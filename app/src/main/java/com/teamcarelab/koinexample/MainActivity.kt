package com.teamcarelab.koinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.teamcarelab.koinexample.`object`.Student
import com.teamcarelab.koinexample.`object`.Teacher
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
                    single { Teacher("teacher-cho") }
                    /* 매번 새로 생성되는 Factory Object 만들기 */
                    factory { Student("student-kim") }
                }
            )
        }

        val teacher : Teacher by inject()
        val student : Student by inject()

        findViewById<TextView>(R.id.textViewTeacher).text = teacher.name
        findViewById<TextView>(R.id.textViewStudent).text = student.name
    }
}