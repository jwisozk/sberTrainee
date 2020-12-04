package com.example.sbertrainee.presenter

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.sbertrainee.R
import com.example.sbertrainee.inrerface.Contract
import com.example.sbertrainee.model.Model
import com.example.sbertrainee.view.TraineeCatalogFragment

class MainPresenter(
    private val view: Contract.MainView,
    model: Model,
    private val fragmentManager: FragmentManager,
    viewLifecycleOwner: LifecycleOwner
) : Contract.MainPresenter {

    init {
        if (model.isAddedViewPagerFragmentLiveData.value == true) {
            view.setTraineeCatalogFragmentVisible()
        }
//            addViewPagerFragment()
        model.isAddedViewPagerFragmentLiveData.observe(viewLifecycleOwner) { value ->
            if (value) {
//                addViewPagerFragment()
                view.setTraineeCatalogFragmentVisible()
            }
        }
    }

//    private fun addViewPagerFragment() {
//        val fragment = TraineeCatalogFragment.newInstance()
//        fragmentManager.beginTransaction()
//            .replace(R.id.fragmentTraineeCatalogContainer, fragment)
//            .commitNow()
//    }

//    override fun onDispatchTouchEvent(event: MotionEvent, currentFocus: View?) {
//        if (event.action == MotionEvent.ACTION_DOWN) {
//            val v: View? = currentFocus
//            if (v is EditText) {
//                val outRect = Rect()
//                v.getGlobalVisibleRect(outRect)
//                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
//                    v.clearFocus()
//                }
//            }
//        }
//    }
}