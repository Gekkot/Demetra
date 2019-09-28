//
//  MarketCenterCell.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class MarketCenterCell: UITableViewCell {

     var buttonAction: ((Any) -> Void)?
    
    @IBOutlet weak var imageMarketCenter: UIImageView!
    @IBOutlet weak var labelMarketCenter: UILabel!
    @IBOutlet weak var goToMapMarker: UIButton!
    @IBAction func goToMapMarkerAction(_ sender: UIButton) {
        buttonAction?(goToMapMarker!)
    }
    
    func printMarketCenter(marketCenter: MarketCenter){
        imageMarketCenter.image = marketCenter.image
        labelMarketCenter.text = marketCenter.name
    }
    
    var actionHandler: ((MarketCenterCell) -> Void)?
    @IBAction func action() {
        actionHandler?(self)
    }
    
}
