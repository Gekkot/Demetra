//
//  MenuViewController.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 28/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class MenuViewController: UIViewController {
    
    @IBOutlet weak var chosenRestaurantImage: UIImageView!
    @IBOutlet weak var chosenRestaurantLabel: UILabel!
    @IBOutlet weak var restaurantMenuTableView: UITableView!
    @IBOutlet weak var CategoriesCollectionView: UICollectionView!
    override func viewDidLoad() {
        super.viewDidLoad()
        CategoriesCollectionView.delegate = self
        CategoriesCollectionView.dataSource = self
        
        restaurantMenuTableView.delegate = self
        restaurantMenuTableView.dataSource = self
        chosenRestaurantImage.image = saveRestaurant.image
        chosenRestaurantLabel.text = saveRestaurant.name
    }
    
    func showMenuTableView(number: Int) -> Restaurant{
        return saveRestaurant
    }

}

extension MenuViewController: UICollectionViewDelegate, UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 6
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CategoriesCell", for: indexPath) as! CategoriesCollectionViewCell
        cell.buttonAction = { (cell) in
            self.showMenuTableView(number: indexPath.row)
        }
        return cell
    }
    
    
}

extension MenuViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "RestaurantMenuCell") as! RestaurantMenuCell
        
        
        return cell
    }
    
    
}
