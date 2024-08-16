//
//  AppDelegate.swift
//  iosApp
//
//  Created by alexandru.vlad on 04.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import UIKit
import Shared

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        MainDIKt.doInitKoin()
        return true
    }
}
