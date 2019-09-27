//
//  MenuModel.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import Foundation
import UIKit

enum MenuModel: Int, CustomStringConvertible {
    case Stocks
    case Recommendations
    case NewPlaces
    case Settings
    
    var description: String {
        switch self{
             case .Stocks: return "Акции"
             case .Recommendations: return "Рекомендации"
             case .NewPlaces: return "Новые места"
             case .Settings: return "Настройки"
        }
    }
    
    var image: UIImage {
        switch self{
        case .Stocks: return UIImage(named: "eventIconMenu") ?? UIImage()
        case .Recommendations: return UIImage(named: "recommendationIconMenu") ?? UIImage()
        case .NewPlaces: return UIImage(named: "newPlaceIconMenu") ?? UIImage()
        case .Settings: return UIImage(named: "settingIconMenu") ?? UIImage()
        }
    }
}
