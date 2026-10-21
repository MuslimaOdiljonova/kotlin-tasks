class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize().safeDrawingPadding()) {
                    // show ONE demo at a time, change this line for each question
                    LectureTree()
                    // PaddingDemo()
                    // OffsetDemo()
                    // ColorDemo()
                    // ParallaxSlow()
                    // ParallaxFast()
                    // ScrollToTopNaive()
                    // ScrollToTopDerived()
                    // ParentByValue()
                    // Parent()
                    // CyclicDemo()
                    // CyclicFixed()
                    // StackBelowDemo()
                }
            }
        }
    }
}
