package pl.klamborowski.akratebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AkRateBar extends LinearLayout {
	
	private int activeImageResource;
	private int inactiveImageResource;
	
	private int rateStarsNumber;
	
	private float imageSize; 

	private int rate = 0;
	public AkRateBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		 TypedArray a = context.getTheme().obtainStyledAttributes(
			        attrs,
			        R.styleable.AKRateBar,
			        0, 0);
		 
		 
		activeImageResource = a.getResourceId(R.styleable.AKRateBar_activeImage,
				R.drawable.gwiazdka_10);
		inactiveImageResource = a.getResourceId(R.styleable.AKRateBar_inactiveImage,
				R.drawable.gwiazdka_0);

		rateStarsNumber = a.getInt(R.styleable.AKRateBar_rateStarsNumber, 5);
		imageSize = a.getDimension(R.styleable.AKRateBar_imageSize, 20);
		  
		
		a.recycle();
		setImages();
		
		
	}
	
	private void setImages(){
		
		this.removeAllViews();
		
		for(int i =0; i<rateStarsNumber; i++){
			ImageView tempImageView = new ImageView(this.getContext());
			
			LayoutParams lp = new LayoutParams((int)imageSize, (int)imageSize, 0);
			
			tempImageView.setLayoutParams(lp);
			tempImageView.setImageResource(inactiveImageResource);
			tempImageView.setTag(i);
			tempImageView.setId(i);
			
			tempImageView.setClickable(true);
			tempImageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int selectedId = v.getId();
					
					LinearLayout ll = (LinearLayout) v.getParent();
					for (int j = 0; j < rateStarsNumber; j++) {
						if (j <= selectedId) {
							((ImageView) ll.findViewById(j))
									.setImageResource(activeImageResource);
						} else {
							((ImageView) ll.findViewById(j))
									.setImageResource(inactiveImageResource);
						}
					}
					rate = (selectedId +1);
					
				};
			});
			
			this.addView(tempImageView);
		}
	}
	
	public void setInactiveImageResource(int resourceId){
		inactiveImageResource = resourceId;
	}
	
	public void setActiveImageResource(int resourceId){
		activeImageResource = resourceId;
	}
	
	
	public int getRate(){
		return rate;
	}

}
