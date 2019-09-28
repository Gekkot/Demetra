//
//  MarketCenterCell.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class MarketCenterCell: UITableViewCell {

    @IBOutlet weak var imageMarketCenter: UIImageView!
    @IBOutlet weak var labelMarketCenter: UILabel!
    
    func printMarketCenter(marketCenter: MarketCenter){
        imageMarketCenter.image = marketCenter.image
        labelMarketCenter.text = marketCenter.name
    }

}
