package com.gorelov.anton.nytimes.homework4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gorelov.anton.nytimes.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class MainActivity : AppCompatActivity() {

    private lateinit var stepLeft: RunnableStep
    private lateinit var stepRight: RunnableStep

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()

        val lock = ReentrantLock()
        val step = AtomicBoolean(false)

        stepLeft = LeftLeg(lock, step, this)
        stepRight = RightLeg(lock, step, this)

        Thread(stepLeft).start()
        Thread(stepRight).start()
    }

    public override fun onStop() {
        super.onStop()
        stepLeft.stop()
        stepRight.stop()
    }

    fun setText(text: String) = runOnUiThread { step_text.text = text }

    private inner class LeftLeg(lock: Lock,
                                step: AtomicBoolean,
                                activity: MainActivity
    ) : RunnableStep(
            "Left step",
            false,
            activity,
            lock,
            step
    )

    private inner class RightLeg(
            lock: Lock,
            step: AtomicBoolean,
            activity: MainActivity
    ) : RunnableStep(
            "Right step",
            true,
            activity,
            lock,
            step
    )

    private abstract inner class RunnableStep(
            private val stepName: String,
            private val stepPhase: Boolean,
            activity: MainActivity,
            private val lock: Lock,
            private val step: AtomicBoolean
    ) : Runnable {
        private var isRunning = true
        private val activity: WeakReference<MainActivity> = WeakReference(activity)

        override fun run() {
            while (isRunning) {
                lock.lock()
                if (step.get() == stepPhase) {
                    println(stepName)
                    activity.get()?.setText(stepName)
                    step.set(!step.get())
                }
                lock.unlock()
            }
        }

        fun stop() {
            this.isRunning = false;
        }
    }
}
