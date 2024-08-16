import SwiftUI
import Shared

struct CreateQuizScreen: View {

    @ObservedObject var viewModel = IOSViewModel<
    CreateQuizIntent,
    CreateQuizState,
    CreateQuizEvent
    >(commonViewModel: CreateQuizViewModelFactory().create())

    var body: some View {
        if let state = viewModel.state {
            if (state.loading) {
                ProgressView()
            }
            VStack {
                NumberOfQuestionsSelector(nrQuestions: state.nrOfQuestions) {
                    viewModel.handleIntent(intent: CreateQuizIntent.NumberOfQuestionsChange(nrQuestions: $0))
                }
                QuestionTypeSelector(selectedType: state.selectedType) {
                    viewModel.handleIntent(intent: CreateQuizIntent.TypeSelect(type: $0))
                }
                DifficultySelector(selectedDifficulty: state.selectedDifficulty) {
                    viewModel.handleIntent(intent: CreateQuizIntent.DifficultySelect(difficulty: $0))
                }
                CategorySelector(categories: state.categories, selectedCategory: state.selectedCategory) {
                    viewModel.handleIntent(intent: CreateQuizIntent.CategorySelect(category: $0))
                }
                Button("Create Quiz") {
                    viewModel.handleIntent(intent: CreateQuizIntent.StartQuizButtonClick())
                }
            }
                    .padding(8)
        }
    }
}

struct NumberOfQuestionsSelector: View {
    let onChange: (Int32) -> Void

    @State private var nrQuestions: Int32
    private var intProxy: Binding<Double> {
        Binding<Double>(get: { Double(nrQuestions) }, set: { nrQuestions = Int32($0) })
    }

    init(nrQuestions: Int32, onChange: @escaping (Int32) -> Void) {
        self.onChange = onChange
        self.nrQuestions = nrQuestions
    }

    var body: some View {
        VStack(content: {
            Text("The quiz will contain \(nrQuestions) questions")
            Text("Move the slider to change the number of questions")
            Slider(value: intProxy,
                    in: 0.0...50.0,
                    step: 1,
                    onEditingChanged: { editing in
                        onChange(nrQuestions)
                    })
        })
    }
}

struct QuestionTypeSelector: View {
    let onChange: (QuestionType) -> Void

    @State private var selectedTypeIndex: Int32

    init(selectedType: QuestionType, onChange: @escaping (QuestionType) -> Void) {
        self.onChange = onChange
        selectedTypeIndex = selectedType.ordinal
    }

    var body: some View {
        VStack {
            Text("Select the type of the questions")
            Picker("QuestionType", selection: $selectedTypeIndex) {
                ForEach(0...QuestionType.values().size - 1, id: \.self) {
                    Text(QuestionType.values().get(index: $0)!.name)
                }
            }
                    .pickerStyle(.segmented)
                    .onChange(of: selectedTypeIndex) {
                        onChange(QuestionType.values().get(index: $0)!)
                    }

        }
    }
}

struct DifficultySelector: View {
    let onChange: (Difficulty) -> Void

    @State private var selectedDifficultyIndex: Int32

    init(selectedDifficulty: Difficulty, onChange: @escaping (Difficulty) -> Void) {
        self.onChange = onChange
        selectedDifficultyIndex = selectedDifficulty.ordinal
    }

    var body: some View {
        VStack {
            Text("Select the difficulty of the questions")
            Picker("Difficulty", selection: $selectedDifficultyIndex) {
                ForEach(0...Difficulty.values().size - 1, id: \.self) {
                    Text(Difficulty.values().get(index: $0)!.name)
                }
            }
                    .pickerStyle(.segmented)
                    .onChange(of: selectedDifficultyIndex) {
                        onChange(Difficulty.values().get(index: $0)!)
                    }

        }
    }
}

struct CategorySelector: View {
    let categories: [Shared.Category]
    let onChange: (Shared.Category) -> Void

    @State private var selectedCategory: Shared.Category

    init(categories: [Shared.Category], selectedCategory: Shared.Category, onChange: @escaping (Shared.Category) -> Void) {
        self.categories = categories
        self.onChange = onChange
        self.selectedCategory = selectedCategory
    }

    var body: some View {
        VStack {
            Text("Select a category from which you will receive questions")
            Picker("Categories", selection: $selectedCategory) {
                ForEach(categories, id: \.self) {
                    Text($0.name).tag($0)
                }
            }
                    .pickerStyle(.wheel)
                    .onChange(of: selectedCategory, perform: onChange)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        CreateQuizScreen()
    }
}
