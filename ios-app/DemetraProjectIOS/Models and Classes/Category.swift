//
//  Category.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 29/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import Foundation

class Category {
    var name: String
    var description: String
    var id: Int
    var menuPositions: [[String: Any]]
    
    init(id: Int, name: String, description: String, menuPositions: [[String: Any]]){
        self.id = id
        self.name = name
        self.description = description
        self.menuPositions = menuPositions
    }
}
