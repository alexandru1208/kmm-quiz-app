//
// Created by alexandru.vlad on 04.01.2022.
// Copyright (c) 2022 orgName. All rights reserved.
//

import shared

class IOSViewModel<I: Intent, S: UIState, E: Event>: ObservableObject {

    @Published var events: E? = nil
    @Published var state: S? = nil

    private let commonViewModel: CommonViewModel

    init(commonViewModel: CommonViewModel) {
        self.commonViewModel = commonViewModel
        commonViewModel.events.watch { event in
            self.events = event as? E
        }

        commonViewModel.state.watch { state in
            self.state = state as? S
        }
    }

    func onIntent(intent: I) {
        commonViewModel.onIntent(intent: intent)
    }

    deinit {
        commonViewModel.clear()
    }
}