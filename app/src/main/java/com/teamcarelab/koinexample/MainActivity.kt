package com.teamcarelab.koinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.teamcarelab.koinexample.`object`.Student
import com.teamcarelab.koinexample.`object`.Teacher
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidContext(this@MainActivity)
            modules(
                module {
                    /* 하나만 생성되고 재활용되는 Singleton Object 만들기 */
                    single(named("cho")) { Student("student-cho") }
                    single(named("kim")) { Student("student-kim") }
                }
            )
        }

        val studentCho : Student by inject(named("cho"))
        val studentKim : Student by inject(named("kim"))

        findViewById<TextView>(R.id.textViewTeacher).text = studentCho.name
        findViewById<TextView>(R.id.textViewStudent).text = studentKim.name
    }
}