package com.example.carsapp.ui.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.example.carsapp.R
import com.example.carsapp.databinding.CarsListErrorBinding

/**
 *
 */
internal class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CarsListErrorBinding

    /**
     * The icon refers an illustration resume of error.
     * @see R.styleable.errorCardView_error_icon
     */
    var icon: Int? = null
        set(value) {
            field = value

            // TODO: Test this logic
            if (value != null) {
                binding.errorImage.setImageResource(value)
            } else {
                binding.errorImage.setImageResource(R.drawable.ic_signal_wifi_off)
            }
        }

    /**
     * The description about the error.
     * @see R.styleable.errorCardView_error_description
     */
    var description: String? = "Desculpe, ocorreu um erro com a sua internet e já estamos tentando reconectar. Caso demore, por favor, tente novamente mais tarde."
        set(value) {
          field = value
          binding.errorDescriprion.text = value
        }

    /**
     * If the action label is visible
     * @see R.styleable.errorCardView_error_action_label
     */
    var actionLabelVisibility: Boolean = true
        set(value) {
            field = value
            binding.tryAgainButton.isVisible = value
        }

    /**
     * The text label of action button.
     * @see R.styleable.errorCardView_error_action_label
     */
    var actionLabel: String? = "tentar novamente"
        set(value) {
            field = value
            binding.tryAgainButton.text = value
        }

    private var onRetryButtonClickedLister: OnRetryButtonClickedLister? = null

    init {
        binding = CarsListErrorBinding.inflate(
            /* inflater = */ LayoutInflater.from(context),
            /* parent = */ this,
            /* attachToParent = */ true
        )

        initializeAttributes(attrs, defStyleAttr, defStyleRes)
        setupListeners()
    }

    private fun setupListeners() {
        binding.tryAgainButton.setOnClickListener {
            onRetryButtonClickedLister?.onRetryButtonClicked()
        }
    }

    /**
     * Set a listener for when the user clicks the "retry" button of this card.
     * @param listener Listener.
     */
    fun setOnRetryButtonClickedListener(listener: OnRetryButtonClickedLister?) {
        onRetryButtonClickedLister = listener
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val errorCard = this

        context.withStyledAttributes(attrs, R.styleable.errorCardView, defStyleAttr, defStyleRes) {
            icon = getInt(R.styleable.errorCardView_error_icon, errorCard.icon ?: R.drawable.ic_signal_wifi_off)
            description = getString(R.styleable.errorCardView_error_description) ?: errorCard.description
            actionLabel = getString(R.styleable.errorCardView_error_action_label) ?: errorCard.actionLabel
            actionLabelVisibility = getBoolean(R.styleable.errorCardView_error_action_visibility, errorCard.isVisible)
        }
    }

    /**
     * Listener interface for the click event of the "retry" button.
     */
    fun interface OnRetryButtonClickedLister {
        /**
         * Callback for when the user click the "retry" button.
         */
            fun onRetryButtonClicked()
    }

}