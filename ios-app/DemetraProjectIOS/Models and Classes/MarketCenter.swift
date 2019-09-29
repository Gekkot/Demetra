//
//  MarketCenter.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import Foundation
import UIKit


class MarketCenter {
    
    var image: UIImage
    var id: Int
    var name: String
    var restaurantsIds: [Int]
    var city: String
    var address: String
    var clusterId: Int
    var longitude: Double
    var latitude: Double
    var url: String
    
    init(image: UIImage, id: Int, name: String, restaurantsIds: [Int], city: String, address: String, clusterId: Int, longitude: Double, latitude: Double, url: String){
        self.image = image
        self.id = id
        self.name = name
        self.restaurantsIds = restaurantsIds
        self.city = city
        self.address = address
        self.clusterId = clusterId
        self.longitude = longitude
        self.latitude = latitude
        self.url = url
    }
}
