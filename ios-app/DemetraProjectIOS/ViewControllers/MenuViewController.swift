//
//  MenuViewController.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 28/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit
var temp = 0
class MenuViewController: UIViewController {
    
    var foods: [[Food]] = [[]]
    var categories: [Category] = []
    var countOfCategories: Int?
    
    @IBAction func backToRestaurantViewController(_ sender: UIButton) {
        present(RestaurantViewController(), animated: false, completion: nil)
    }
    @IBAction func showBasketAction(_ sender: UIButton) {
    }
    @IBOutlet weak var basketButton: UIButton!
    @IBOutlet weak var chosenRestaurantImage: UIImageView!
    @IBOutlet weak var chosenRestaurantLabel: UILabel!
    @IBOutlet weak var restaurantMenuTableView: UITableView!
    @IBOutlet weak var CategoriesCollectionView: UICollectionView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        chosenRestaurantImage.image = saveRestaurant.image
        chosenRestaurantLabel.text = saveRestaurant.name
        createMenu()
        CategoriesCollectionView.delegate = self
        CategoriesCollectionView.dataSource = self
        restaurantMenuTableView.delegate = self
        restaurantMenuTableView.dataSource = self
    }
    func createMenu(){
        guard let url = URL(string: "http://172.20.42.77:4004/menu") else {print("Invalid URL"); return}
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard let dataResponse = data, error == nil else{print(error?.localizedDescription ?? "Response error"); return }
            do{
                let json = try JSONSerialization.jsonObject(with: dataResponse, options: []) as! Dictionary<String, Any>
                let jsonArray = json["data"] as! [[String: Any]]
                for i in 0..<jsonArray.count{
                    guard let id = jsonArray[i]["id"] as? Int else {print("Invalid \(i) id"); return}
                    guard let name = jsonArray[i]["name"] as? String else {print("Invalid \(i) name"); return}
                    guard let menuPositionsArray = jsonArray[i]["menuPositions"] as? [[String: Any]] else {print("Invalid \(i) menuPositions"); return}
                    for j in 0..<menuPositionsArray.count{
                        guard let id1 = menuPositionsArray[j]["id"] as? Int else {return}
                        guard let name1 = menuPositionsArray[j]["name"] as? String else {return}
                        guard let description1 = menuPositionsArray[j]["description"] as? String else {return}
                        guard let price1 = menuPositionsArray[j]["price"] as? Double else {return}
                        let food = Food(image: #imageLiteral(resourceName: "recommendationIconMenu"), id: id1, name: name1, description: description1, price: price1)
                        self.foods[0].append(food)
                    }
                    guard let description = jsonArray[i]["description"] as? String else {print("Invalid \(i) description"); return}
                    let category = Category(id: id, name: name, description: description, menuPositions: menuPositionsArray)
                    self.categories.append(category)
                }
            }catch let error{print(error.localizedDescription); return}
        }.resume()
    }
    
    func showMenuTableView(number: Int){
        temp = number
    }
}

extension MenuViewController: UICollectionViewDelegate, UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return categories.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CategoriesCell", for: indexPath) as! CategoriesCollectionViewCell
        cell.buttonAction = { (cell) in
            self.showMenuTableView(number: indexPath.row)
        }
        cell.categoryButton.titleLabel?.text = categories[indexPath.row].name
        return cell
    }
    
}

extension MenuViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return foods.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let food = foods[temp]
        let cell = tableView.dequeueReusableCell(withIdentifier: "RestaurantMenuCell") as! RestaurantMenuCell
        cell.numberOfFood.text = "0"
        cell.printFood(food: food[0])
        return cell
    }
    
}
