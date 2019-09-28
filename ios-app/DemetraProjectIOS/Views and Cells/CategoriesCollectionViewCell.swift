//
//  CategoriesCollectionViewCell.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 28/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class CategoriesCollectionViewCell: UICollectionViewCell {
    
    var buttonAction: ((Any) -> Void)?
    
    @IBOutlet weak var categoryButton: UIButton!
    @IBAction func categoryButtonAction(_ sender: UIButton) {
        buttonAction?(categoryButton!)
    }
    
}
