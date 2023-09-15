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
 * View for displaying inline errors.
 *
 * @param context The context in which to create the view.
 * @param attrs View attributes, used for styling and general view customization.
 * @param defStyleAttr Attribute used to set a default style for this View (usually defined in the theme). o for none.
 * @param defStyleRes Default style resource to apply to this View if `defStyleAttr` is unused. 0 for none.
 */
internal class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CarsListErrorBinding
    private var onRetryButtonClickedLister: OnRetryButtonClickedListenerTest? = null

    /**
     * Error icon.
     * @see R.styleable.errorCardView_error_icon
     */
    var icon: Int? = null
        set(value) {
            field = value

            when (value != null) {
                true -> binding.errorImage.setImageResource(value)
                false -> binding.errorImage.setImageResource(R.drawable.ic_close)
            }
        }

    /**
     * Error description.
     * @see R.styleable.errorCardView_error_description
     */
    var description: String? = context.getString(R.string.error_description)
        set(value) {
          field = value
          binding.errorDescriprion.text = value
        }

    /**
     * Action label visibility.
     * @see R.styleable.errorCardView_error_action_label
     */
    var actionLabelVisibility: Boolean = true
        set(value) {
            field = value
            binding.tryAgainButton.isVisible = value
        }

    /**
     * Text label value for action button.
     * @see R.styleable.errorCardView_error_action_label
     */
    var actionLabel: String? = context.getString(R.string.try_again)
        set(value) {
            field = value
            binding.tryAgainButton.text = value
        }

    init {
        binding = CarsListErrorBinding.inflate(
            /* inflater = */ LayoutInflater.from(context),
            /* parent = */ this,
            /* attachToParent = */ true
        )

        initializeAttributes(attrs, defStyleAttr, defStyleRes)
        setupAction()
    }

    /**
     * Set a listener for when the user clicks the "retry" button of this card.
     * @param listener Listener.
     */
    fun setOnRetryButtonClickedListener(listener: OnRetryButtonClickedListenerTest?) {
        onRetryButtonClickedLister = listener
    }

    private fun setupAction() {
        binding.tryAgainButton.setOnClickListener {
            onRetryButtonClickedLister?.onRetryButtonClicked()
        }
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val errorCard = this

        // You can create a generic error instead of put random values
        context.withStyledAttributes(attrs, R.styleable.errorCardView, defStyleAttr, defStyleRes) {
            icon = getInt(
                R.styleable.errorCardView_error_icon,
                errorCard.icon ?: R.drawable.ic_signal_wifi_off
            )

            description = getString(
                R.styleable.errorCardView_error_description
            ) ?: errorCard.description

            actionLabel = getString(
                R.styleable.errorCardView_error_action_label
            ) ?: errorCard.actionLabel

            actionLabelVisibility = getBoolean(
                R.styleable.errorCardView_error_action_visibility,
                errorCard.isVisible
            )
        }
    }
}
