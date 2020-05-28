package com.example.sampledrawer

import android.os.Bundle

interface FragmentCallback {
    fun onFragmentSelected(position: Int, bundle: Bundle)
}
