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
                    single { Student("student-kim") }
                    single { Teacher("teacher-cho", get()) }
                }
            )
        }

        val teacherCho: Teacher by inject()

        findViewById<TextView>(R.id.textViewTeacher).text = teacherCho.name
        findViewById<TextView>(R.id.textViewStudent).text = teacherCho.teachingStudent.name
    }
}