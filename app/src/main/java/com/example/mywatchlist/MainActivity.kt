class MainActivity : AppCompatActivity() {

    private val movieNames = mutableListOf<String>()
    private val categories = mutableListOf<String>()
    private val ratings = mutableListOf<Int>()
    private val comments = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.btnAdd)
        val nextScreenButton = findViewById<Button>(R.id.btnNext)
        val exitButton = findViewById<Button>(R.id.btnExit)

        addButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.editmovieName).text.toString()
            val category = findViewById<EditText>(R.id.editCategory).text.toString()
            val ratingStr = findViewById<EditText>(R.id.editRating).text.toString()
            val comment = findViewById<EditText>(R.id.editComment).text.toString()

            if (name.isBlank() || category.isBlank() || ratingStr.isBlank() || comment.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val parsedRating = ratingStr.toIntOrNull()
            if (parsedRating == null || parsedRating < 1) {
                Toast.makeText(this, "Enter a valid rating", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            movieNames.add(name)
            categories.add(category)
            ratings.add(parsedRating)
            comments.add(comment)

            Toast.makeText(this, "Movie added!", Toast.LENGTH_SHORT).show()
        }

        nextScreenButton.setOnClickListener {
            val intent = Intent(this, PackingListActivity::class.java).apply {
                putStringArrayListExtra("movies", ArrayList(movieNames))
                putStringArrayListExtra("categories", ArrayList(categories))
                putIntegerArrayListExtra("ratings", ArrayList(ratings))
                putStringArrayListExtra("comments", ArrayList(comments))
            }
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}