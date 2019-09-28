//
//  SideMenuViewController.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class SideMenuViewController: UIViewController {
    
    var sideMenuTableView: UITableView!

    var imageInMenu: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.backgroundColor = #colorLiteral(red: 0.2549019754, green: 0.2745098174, blue: 0.3019607961, alpha: 1)
        configureSideMenuImageView()
        configureSideMenuTableView()
        
        
    }
    
    func configureSideMenuTableView(){
        sideMenuTableView = UITableView()
        sideMenuTableView.delegate = self
        sideMenuTableView.dataSource = self
        sideMenuTableView.register(MenuTableCell.self, forCellReuseIdentifier: MenuTableCell.reuseId)
        view.addSubview(sideMenuTableView)
        sideMenuTableView.frame = view.bounds
        
        let heightCoefficient = ContainerViewController().view.frame.height
        
        sideMenuTableView.separatorStyle = .none
        sideMenuTableView.rowHeight = heightCoefficient * 0.1
        sideMenuTableView.backgroundColor = #colorLiteral(red: 0.2549019754, green: 0.2745098174, blue: 0.3019607961, alpha: 1)
        sideMenuTableView.frame = view.frame.offsetBy(dx: 0, dy: imageInMenu.frame.height)
    }
    
    func configureSideMenuImageView(){
        imageInMenu = UIImageView()
        imageInMenu.image = UIImage(named: "menuIconDemetra")
        view.addSubview(imageInMenu)
        imageInMenu.frame = CGRect(x: -10, y: 30, width: 250, height: 200)
    
    }
}

extension SideMenuViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 6
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = sideMenuTableView.dequeueReusableCell(withIdentifier: MenuTableCell.reuseId) as! MenuTableCell
        let menuModel = MenuModel(rawValue: indexPath.row)
        cell.iconImageView.image = menuModel?.image
        cell.myLabel.text = menuModel?.description
        if indexPath.row == 4 || indexPath.row == 5 {
            view.willRemoveSubview(imageInMenu)
            if indexPath.row == 4{
                cell.myLabel.text = "Помощь"
            }
            if indexPath.row == 5{
                cell.myLabel.text = "О программе"
            }
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        sideMenuTableView.deselectRow(at: indexPath, animated: true)
    }
}
