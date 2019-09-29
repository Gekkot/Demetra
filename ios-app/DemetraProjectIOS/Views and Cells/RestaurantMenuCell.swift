//
//  RestaurantMenuCell.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 28/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class RestaurantMenuCell: UITableViewCell {

    @IBOutlet weak var addTopping: UIButton!
    @IBOutlet weak var minusNumberOfFood: UIButton!
    @IBOutlet weak var plusNumberOfFood: UIButton!
    @IBOutlet weak var foodImageView: UIImageView!
    @IBOutlet weak var foodLabel: UILabel!
    @IBOutlet weak var numberOfFood: UILabel!
    @IBAction func minusNumberOfFoodAction(_ sender: UIButton) {
        var temp = Int(numberOfFood.text!)
        temp = temp! - 1
        if temp == -1{
            temp = 0
        }
        numberOfFood.text = String(temp!)
    }
    @IBAction func plusNumberOfFoodAction(_ sender: UIButton) {
        var temp = Int(numberOfFood.text!)
        temp = temp! + 1
        numberOfFood.text = String(temp!)
    }
    @IBAction func addToppingAction(_ sender: UIButton) {
    }
    func printFood(food: Food){
        foodImageView.image = food.image
        foodLabel.text = food.name
    }
    
    
}
