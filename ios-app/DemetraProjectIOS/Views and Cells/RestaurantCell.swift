//
//  RestaurantCell.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class RestaurantCell: UITableViewCell {

    @IBOutlet weak var restaurantImage: UIImageView!
    @IBOutlet weak var restaurantLabel: UILabel!
    
    func printRestaurant(restaurant: Restaurant){
        restaurantImage.image = restaurant.image
        restaurantLabel.text = restaurant.name
    }

}
