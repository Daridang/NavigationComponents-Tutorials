package com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter.FragmentTransactionCallback.OnPostEventListener
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.R
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.blankfragment.LoginFragment1
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.factory.NavHostFragmentFactory
import com.smarttoolfactory.tutorial8_2dynamicfeatures_complexarchitecture.fragment.navhost.*


/**
 * FragmentStateAdapter to contain ViewPager2 fragments inside another fragment.
 *
 * * 🔥 Create FragmentStateAdapter with viewLifeCycleOwner instead of Fragment to make sure
 * that it lives between [Fragment.onCreateView] and [Fragment.onDestroyView] while [View] is alive
 *
 * * https://stackoverflow.com/questions/61779776/leak-canary-detects-memory-leaks-for-tablayout-with-viewpager2
 */
class ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


//    private val fragmentTransactionCallback = object : FragmentTransactionCallback() {
//
//        override fun onFragmentMaxLifecyclePreUpdated(
//            fragment: Fragment,
//            maxLifecycleState: Lifecycle.State
//        ) = if (maxLifecycleState == Lifecycle.State.RESUMED) {
//
//            // This fragment is becoming the active Fragment - set it to
//            // the primary navigation fragment in the OnPostEventListener
//            OnPostEventListener {
//                fragment.parentFragmentManager.commitNow {
//                    setPrimaryNavigationFragment(fragment)
//                }
//            }
//
//        } else {
//            super.onFragmentMaxLifecyclePreUpdated(fragment, maxLifecycleState)
//        }
//    }

    init {
        // Add a FragmentTransactionCallback to handle changing
        // the primary navigation fragment
        registerFragmentTransactionCallback(object : FragmentTransactionCallback() {

            override fun onFragmentMaxLifecyclePreUpdated(
                fragment: Fragment,
                maxLifecycleState: Lifecycle.State
            ) = if (maxLifecycleState == Lifecycle.State.RESUMED) {

                // This fragment is becoming the active Fragment - set it to
                // the primary navigation fragment in the OnPostEventListener
                OnPostEventListener {
                    fragment.parentFragmentManager.commitNow {
                        setPrimaryNavigationFragment(fragment)
                    }
                }

            } else {
                super.onFragmentMaxLifecyclePreUpdated(fragment, maxLifecycleState)
            }
        })
    }

    fun registerFragmentTransactionCallback() {
//        registerFragmentTransactionCallback(fragmentTransactionCallback)
    }

    fun unregisterFragmentTransactionCallback() {
//        unregisterFragmentTransactionCallback(fragmentTransactionCallback)
    }

    override fun getItemCount(): Int = 6

//    override fun createFragment(position: Int): Fragment {
//
//        return when (position) {
//            0 -> PostVerticalNavHostFragment()
//            1 -> PostHorizontalNavHostFragment()
//            2 -> PostGridNavHostFragment()
//            3 -> PostStaggeredNavHostFragment()
//            4 -> NotificationHostFragment()
//            else -> LoginFragment1()
//
//        }
//    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {

            // Vertical NavHost Post Fragment Container
            0 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_post_vertical,
                R.id.nested_nav_host_fragment_post_vertical
            )

            // Horizontal NavHost Post Fragment Container
            1 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_post_horizontal,
                R.id.nested_nav_host_fragment_post_horizontal
            )

            // Grid Post NavHost Fragment Container
            2 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_post_grid,
                R.id.nested_nav_host_fragment_post_grid
            )

            // Staggered Post NavHost Fragment Container
            3 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_post_staggered,
                R.id.nested_nav_host_fragment_post_staggered
            )

            // Notification NavHost Fragment Container
            4 -> NavHostContainerFragment.newInstance(
                R.layout.fragment_navhost_notification,
                R.id.nested_nav_host_fragment_notification
            )
            else -> LoginFragment1()

        }
    }

    // FIXME There is an issue with ViewPager2 and FragmentFactory when app is rotated
    /*
        🔥🔥🔥 It creates the last created fragment created prior to rotation, it's expected
        but a bug, fix it.

        ViewPager2 creates fragments after rotation which should not be created and destroys them
        after 10 seconds if you don't swipe.

     */

//    override fun createFragment(position: Int): Fragment {
//
//        val classLoader = fragment.requireActivity().classLoader
//
//        return when (position) {
//            0 -> navHostFragmentFactory.createNavHostFragment(
//                classLoader,
//                NavHostContainerFragment::class.java.name,
//                R.layout.fragment_navhost_post_vertical,
//                R.id.nested_nav_host_fragment_post_vertical,
//                "Post Vertical"
//            )
//            1 -> navHostFragmentFactory.createNavHostFragment(
//                classLoader,
//                NavHostContainerFragment::class.java.name,
//                R.layout.fragment_navhost_post_horizontal,
//                R.id.nested_nav_host_fragment_post_horizontal,
//                "Post Horizontal"
//            )
//            2 -> navHostFragmentFactory.createNavHostFragment(
//                classLoader,
//                NavHostContainerFragment::class.java.name,
//                R.layout.fragment_navhost_post_grid,
//                R.id.nested_nav_host_fragment_post_grid,
//                "Post Grid"
//            )
//            3 -> navHostFragmentFactory.createNavHostFragment(
//                classLoader,
//                NavHostContainerFragment::class.java.name,
//                R.layout.fragment_navhost_post_staggered,
//                R.id.nested_nav_host_fragment_post_staggered,
//                "Post Staggered"
//            )
//            4 -> navHostFragmentFactory.createNavHostFragment(
//                classLoader,
//                NavHostContainerFragment::class.java.name,
//                R.layout.fragment_navhost_notification,
//                R.id.nested_nav_host_fragment_notification,
//                "Notification"
//            )
//            else -> LoginFragment1()
//
//        }
//    }

}