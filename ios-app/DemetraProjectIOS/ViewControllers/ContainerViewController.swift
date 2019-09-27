//
//  ContainerViewController.swift
//  DemetraProjectIOS
//
//  Created by Хафизов Руслан on 27/09/2019.
//  Copyright © 2019 Хафизов Руслан. All rights reserved.
//

import UIKit

class ContainerViewController: UIViewController, MarketCenterViewControllerDelegate {
    
   
    var controller: UIViewController!
    var sideMenuViewController: UIViewController!
    var isMove = false
    override func viewDidLoad() {
        super.viewDidLoad()

        configureMarketCenterViewController()
    }
    
    func configureMarketCenterViewController(){
        let marketCenterViewController = UIStoryboard(name: "Main", bundle: nil).instantiateInitialViewController() as! MarketCenterViewController
        marketCenterViewController.delegate = self
        controller = marketCenterViewController
        view.addSubview(controller.view)
        addChild(controller)
        
    }
    func configureSideMenuViewController(){
        if sideMenuViewController == nil{
            sideMenuViewController = SideMenuViewController()
            view.insertSubview(sideMenuViewController.view, at: 0)
            addChild(sideMenuViewController)
        }
    }
    
    func showViewController(shouldMove: Bool){
        if shouldMove{
            UIView.animate(withDuration: 0.5,
                           delay: 0,
                           usingSpringWithDamping: 0.8,
                           initialSpringVelocity: 0,
                           options: .curveEaseInOut,
                           animations: {
                            self.controller.view.frame.origin.x = self.controller.view.frame.width - self.controller.view.frame.width * 0.2
            },
                           completion: nil)
        }
        else{
            UIView.animate(withDuration: 0.5,
                           delay: 0,
                           usingSpringWithDamping: 0.8,
                           initialSpringVelocity: 0,
                           options: .curveEaseInOut,
                           animations: {
                            self.controller.view.frame.origin.x = 0
            },
                           completion: nil)
        }
    }
    
    //MARK MarketCenterViewControllerDelegate
    
    func toggleMenu() {
        configureSideMenuViewController()
        isMove = !isMove
        showViewController(shouldMove: isMove)
    }
}
