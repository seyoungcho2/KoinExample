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
                    single(named("kim")) { Student("student-kim") }
                    factory(named("cho")) { Student("student-cho") }
                }
            )
        }

        val studentKim1 : Student by inject(named("kim"))
        val studentKim2 : Student by inject(named("kim"))

        val studentCho1 : Student by inject(named("cho"))
        val studentCho2 : Student by inject(named("cho"))

        findViewById<TextView>(R.id.textViewTeacher).text = (studentKim1 === studentKim2).toString() // true
        findViewById<TextView>(R.id.textViewStudent).text = (studentCho1 === studentCho2).toString() // false
    }
}