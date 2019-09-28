//
//  RestaurantViewController.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

var saveRestaurant:Restaurant = Restaurant(image: #imageLiteral(resourceName: "showInMapButton"), id: 0, name: "", city: "", address: "", longitude: 0.0, latitude: 0.0, cityMollId: 0, ownerId: 0)

class RestaurantViewController: UIViewController {

    var restaurants: [Restaurant] = []
    var restaurantsIds: [Int] = []
    
    var countOfRestaurant: Int?
    
    @IBOutlet weak var RestaurantTable: UITableView!
    @IBAction func backToMarketCenter(_ sender: Any) {
        present(ContainerViewController(), animated: true, completion: nil)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        print(saveMarketCenter.restaurantsIds.count)
        createRestaurantArray()
        while restaurants.count != saveMarketCenter.restaurantsIds.count{
            self.RestaurantTable.delegate = self
            self.RestaurantTable.dataSource = self
        }
    }
    
    func createRestaurantArray(){
        restaurantsIds = saveMarketCenter.restaurantsIds
        print(restaurantsIds)
        for i in 0..<restaurantsIds.count{
            guard let url = URL(string: "http://172.20.42.77:4004/restaraunt?id=\(restaurantsIds[i])") else { print("FUCK"); return }
            URLSession.shared.dataTask(with: url) { (data, response, error) in
                guard let dataResponse = data, error == nil else{print(error?.localizedDescription ?? "Response error"); return }
                do{
                    let jsonPrev = try JSONSerialization.jsonObject(with: dataResponse, options: []) as! Dictionary<String,Any>
                    let jsonArray = jsonPrev["data"] as! [String: Any]
                    guard let id = jsonArray["id"] as? Int else { print("id \(i) Error"); return }
                    guard let name = jsonArray["name"] as? String else { print("name \(i) Error"); return }
                    guard let city = jsonArray["city"] as? String else { print("city \(i) Error"); return }
                    guard let address = jsonArray["address"] as? String else { print("address \(i) Error"); return }
                    guard let latitude = jsonArray["latitude"] as? Double else { print("latitude \(i) Error"); return }
                    guard let longitude = jsonArray["longitude"] as? Double else { print("longitude \(i) Error"); return }
                    guard let cityMollID = jsonArray["cityMollID"] as? Int else { print("cityMollID \(i) Error"); return }
                    guard let ownerID = jsonArray["ownerID"] as? Int else { print("ownerID \(i) Error"); return }
                    let image: UIImage = #imageLiteral(resourceName: "eventIconMenu")
                    let currentRestaurant = Restaurant(image: image, id: id, name: name, city: city, address: address, longitude: longitude, latitude: latitude, cityMollId: cityMollID, ownerId: ownerID)
                    self.restaurants.append(currentRestaurant)
                } catch let parsingError {print("error", parsingError)}
            }.resume()
        }
    }
}


extension RestaurantViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return restaurants.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let restaurant = restaurants[indexPath.row]
        saveRestaurant = restaurant
        let cell = tableView.dequeueReusableCell(withIdentifier: "RestaurantCell") as! RestaurantCell
        
        cell.printRestaurant(restaurant: restaurant)
        
        return cell
    }
    
}
