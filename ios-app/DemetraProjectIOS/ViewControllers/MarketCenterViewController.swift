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

    var countOfMarketCenter = 0
    
    var marketCenters: [MarketCenter] = []
    var delegate: MarketCenterViewControllerDelegate?
    @IBOutlet weak var mapView: GMSMapView!
    @IBOutlet weak var marketCenterTable: UITableView!
    
    @IBAction func MenuToggleAction(_ sender: UIButton) {
        delegate?.toggleMenu()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        createMarketCenterArray()
        sleep(1)
        print(countOfMarketCenter)
        //while marketCenters.count != countOfMarketCenter{
            self.marketCenterTable.delegate = self
            self.marketCenterTable.dataSource = self
       // }
        let camera = GMSCameraPosition.camera(withLatitude: 59.93, longitude: 30.3, zoom: 14.0)
        mapView.camera = camera
        self.showMarketCenter(position: mapView.camera.target)
    }
    
    func showMarketCenter(position: CLLocationCoordinate2D){
        let marker = GMSMarker()
        marker.position = position
        marker.map = mapView
    }
    
    func createMarketCenterArray(){
        guard let url = URL(string: "http://91.218.249.70:4004/city_malls") else {print("Invalid URL"); return}
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard let dataResponse = data, error == nil else{print(error?.localizedDescription ?? "Response error"); return }
            do{
                let json = try JSONSerialization.jsonObject(with: dataResponse, options: []) as! Dictionary<String, Any>
                let jsonArray = json["data"] as! [[String: Any]]
                self.countOfMarketCenter = jsonArray.count
                for i in 0..<jsonArray.count{
                    guard let id = jsonArray[i]["id"] as? Int else {print("Invalid \(i) id"); return}
                    guard let name = jsonArray[i]["name"] as? String else {print("Invalid \(i) name"); return}
                    guard let restaurantIdsArray = jsonArray[i]["restarauntIds"] as? [Int] else {print("Invalid \(i) restaurantIds"); return}
                    var restaurantIds: [Int] = []
                    for i in 0..<restaurantIdsArray.count{
                        restaurantIds.append(restaurantIdsArray[i])
                    }
                    guard let city = jsonArray[i]["city"] as? String else {print("Invalid \(i) city"); return}
                    guard let address = jsonArray[i]["address"] as? String else {print("Invalid \(i) address"); return}
                    guard let clusterId = jsonArray[i]["clusterId"] as? Int else {print("Invalid \(i) clusterId"); return}
                    guard let longitude = jsonArray[i]["longitude"] as? Double else {print("Invalid \(i) longitude"); return}
                    guard let latitude = jsonArray[i]["latitude"] as? Double else {print("Invalid \(i) latitude"); return}
                    
                    let image: UIImage!
                    image = #imageLiteral(resourceName: "recommendationIconMenu")
                    
                    let marketCenter = MarketCenter(image: image, id: id, name: name, restaurantsIds: restaurantIds, city: city, address: address, clusterId: clusterId, longitude: longitude, latitude: latitude)
                    self.marketCenters.append(marketCenter)
                }
            }catch let error{print(error.localizedDescription); return}
        }.resume()
    }

}

extension MarketCenterViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return marketCenters.count
        
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let marketCenter = marketCenters[indexPath.row]
        let cell = tableView.dequeueReusableCell(withIdentifier: "MarketCenterCell") as! MarketCenterCell
        cell.printMarketCenter(marketCenter: marketCenter)
        return cell
    }
    
    
}
