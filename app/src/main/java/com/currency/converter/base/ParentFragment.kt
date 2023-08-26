@file:Suppress("UNCHECKED_CAST")
package com.currency.converter.base
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.viewbinding.ViewBinding
import com.currency.converter.base.delegate.EmptyErrorDelegate
import com.currency.converter.base.delegate.EmptyErrorDelegateImp

abstract class ParentFragment<out T : ViewBinding> : Fragment(), EmptyErrorDelegate by EmptyErrorDelegateImp()  {
    private var _binding: ViewBinding? = null
    protected val binding: T get() = _binding as T
    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding
    protected abstract fun initializeComponents(view: View)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = run {
        sharedElementEnterTransition = ChangeBounds()
        _binding = bindingInflater(inflater)
        return _binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents(view)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}