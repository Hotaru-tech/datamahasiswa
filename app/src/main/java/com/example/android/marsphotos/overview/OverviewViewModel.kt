/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.ColorsApi
import com.example.android.marsphotos.network.Colors
import kotlinx.coroutines.launch

enum class ColorsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<ColorsApiStatus>()

    val status: LiveData<ColorsApiStatus> = _status

    private val _photos = MutableLiveData<List<Colors>>()

    val photos: LiveData<List<Colors>> = _photos

    init {
        getUserPhotos()
    }

    private fun getUserPhotos() {

        viewModelScope.launch {
            _status.value = ColorsApiStatus.LOADING
            try {
                _photos.value = ColorsApi.retrofitService.getPhotos()
                _status.value = ColorsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ColorsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
