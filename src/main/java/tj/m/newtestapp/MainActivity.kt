package tj.m.newtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tj.m.newtestapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, MainFragment.newInstance())
                .commitNow()
        }
    }
}
