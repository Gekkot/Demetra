//
//  Restaurant.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import Foundation
import UIKit

class Restaurant {
    
    var image: UIImage
    var id: Int
    var name: String
    var city: String
    var address: String
    var longitude: Double
    var latitude: Double
    var cityMollId: Int
    var ownerId: Int
    var imageUrl: String
    
    init(image: UIImage, id: Int, name: String, city: String, address: String, longitude: Double, latitude: Double, cityMollId: Int, ownerId: Int, imageUrl: String){
        self.image = image
        self.id = id
        self.name = name
        self.city = city
        self.address = address
        self.longitude = longitude
        self.latitude = latitude
        self.cityMollId = cityMollId
        self.ownerId = ownerId
        self.imageUrl = imageUrl
    }
}
