//
//  Food.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 29/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import Foundation
import UIKit

class Food {
    var image: UIImage
    var id: Int
    var name: String
    var description: String
    var price: Double
    
    init(image: UIImage, id: Int, name: String, description: String, price: Double){
        self.image = image
        self.id = id
        self.name = name
        self.description = description
        self.price = price
    }
}
