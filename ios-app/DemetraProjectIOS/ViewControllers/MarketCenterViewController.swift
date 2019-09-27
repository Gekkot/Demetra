//
//  ViewController.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit
import GoogleMaps

protocol MarketCenterViewControllerDelegate {
    func toggleMenu()
}

class MarketCenterViewController: UIViewController {

    var delegate: MarketCenterViewControllerDelegate?
    @IBOutlet weak var mapView: GMSMapView!
    
    @IBAction func MenuToggleAction(_ sender: UIButton) {
        delegate?.toggleMenu()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let camera = GMSCameraPosition.camera(withLatitude: 59.93, longitude: 30.3, zoom: 14.0)
        mapView.camera = camera
        self.showMarketCenter(position: mapView.camera.target)
    }
    
    func showMarketCenter(position: CLLocationCoordinate2D){
        let marker = GMSMarker()
        marker.position = position
        marker.map = mapView
    }

}

