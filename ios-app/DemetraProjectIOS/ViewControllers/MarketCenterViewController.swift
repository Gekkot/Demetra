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

var chooseMarketCenter: MarketCenter = MarketCenter(image: #imageLiteral(resourceName: "basketball"), id: 0, name: "", restaurantsIds: [], city: "", address: "", clusterId: 0, longitude: 0.0, latitude: 0.0)

class MarketCenterViewController: UIViewController {

    var countOfMarketCenter: Int?
    
    var marketCenters: [MarketCenter] = []
    var cameras: [GMSCameraPosition] = []
    var delegate: MarketCenterViewControllerDelegate?
    @IBOutlet weak var mapView: GMSMapView!
    @IBOutlet weak var marketCenterTable: UITableView!
    
    @IBAction func MenuToggleAction(_ sender: UIButton) {
        delegate?.toggleMenu()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        createMarketCenterArray()
        while marketCenters.count != countOfMarketCenter{
            self.marketCenterTable.delegate = self
            self.marketCenterTable.dataSource = self
        }
        for i in 0..<marketCenters.count{
            createMapMarkers(number: i)
        }
    }
    func createMapMarkers(number: Int){
        cameras.append(GMSCameraPosition())
        cameras[number] = GMSCameraPosition.camera(withLatitude: marketCenters[number].latitude, longitude: marketCenters[number].longitude, zoom: 10.0)
        mapView.camera = cameras[number]
        self.showMarketCenter(position: mapView.camera.target, title: marketCenters[number].name, snippet: marketCenters[number].address)
    }
    
    func showMarketCenter(position: CLLocationCoordinate2D, title: String, snippet: String){
        let marker = GMSMarker()
        marker.position = position
        marker.title = title
        marker.snippet = snippet
        marker.map = mapView
    }
    
    func showTappedMarkerCenter(number: Int){
        cameras[number] = GMSCameraPosition.camera(withLatitude: marketCenters[number].latitude, longitude: marketCenters[number].longitude, zoom: 13.0)
        mapView.camera = cameras[number]
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
        
        cell.actionHandler = { [weak self] cell in
            let marketCenterViewController = self?.storyboard?.instantiateInitialViewController() as! MarketCenterViewController
            self!.navigationItem.leftBarButtonItem = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
            self?.navigationController?.pushViewController(marketCenterViewController, animated: true)
            chooseMarketCenter = marketCenter
            tableView.deselectRow(at: indexPath, animated: true)
        }
        cell.buttonAction = { (cell) in
            self.showTappedMarkerCenter(number: indexPath.row)
        }
        return cell
    }
    
    
}
