package com.example.mr_time;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SlidingLayout extends RelativeLayout implements OnTouchListener {

	/**
	 * 婊氬姩鏄剧ず鍜岄殣钘忓乏渚у竷灞�鏃讹紝鎵嬫寚婊戝姩闇�瑕佽揪鍒扮殑閫熷害銆�
	 */
	public static final int SNAP_VELOCITY = 2000;

	/**
	 * 灞忓箷瀹藉害鍊笺��
	 */
	private int screenWidth;

	/**
	 * 鍙充晶甯冨眬鏈�澶氬彲浠ユ粦鍔ㄥ埌鐨勫乏杈圭紭銆�
	 */
	private int leftEdge = 0;

	/**
	 * 鍙充晶甯冨眬鏈�澶氬彲浠ユ粦鍔ㄥ埌鐨勫彸杈圭紭銆�
	 */
	private int rightEdge = 0;

	/**
	 * 鍦ㄨ鍒ゅ畾涓烘粴鍔ㄤ箣鍓嶇敤鎴锋墜鎸囧彲浠ョЩ鍔ㄧ殑鏈�澶у�笺��
	 */
	private int touchSlop;

	/**
	 * 璁板綍鎵嬫寚鎸変笅鏃剁殑妯潗鏍囥��
	 */
	private float xDown;

	/**
	 * 璁板綍鎵嬫寚鎸変笅鏃剁殑绾靛潗鏍囥��
	 */
	private float yDown;

	/**
	 * 璁板綍鎵嬫寚绉诲姩鏃剁殑妯潗鏍囥��
	 */
	private float xMove;

	/**
	 * 璁板綍鎵嬫寚绉诲姩鏃剁殑绾靛潗鏍囥��
	 */
	private float yMove;

	/**
	 * 璁板綍鎵嬫満鎶捣鏃剁殑妯潗鏍囥��
	 */
	private float xUp;

	/**
	 * 宸︿晶甯冨眬褰撳墠鏄樉绀鸿繕鏄殣钘忋�傚彧鏈夊畬鍏ㄦ樉绀烘垨闅愯棌鏃舵墠浼氭洿鏀规鍊硷紝婊戝姩杩囩▼涓鍊兼棤鏁堛��
	 */
	private boolean isLeftLayoutVisible;

	/**
	 * 鏄惁姝ｅ湪婊戝姩銆�
	 */
	private boolean isSliding;

	/**
	 * 宸︿晶甯冨眬瀵硅薄銆�
	 */
	private View leftLayout;

	/**
	 * 鍙充晶甯冨眬瀵硅薄銆�
	 */
	private View rightLayout;

	/**
	 * 鐢ㄤ簬鐩戝惉渚ф粦浜嬩欢鐨刅iew銆�
	 */
	private View mBindView;

	/**
	 * 宸︿晶甯冨眬鐨勫弬鏁帮紝閫氳繃姝ゅ弬鏁版潵閲嶆柊纭畾宸︿晶甯冨眬鐨勫搴︼紝浠ュ強鏇存敼leftMargin鐨勫�笺��
	 */
	private MarginLayoutParams leftLayoutParams;

	/**
	 * 鍙充晶甯冨眬鐨勫弬鏁帮紝閫氳繃姝ゅ弬鏁版潵閲嶆柊纭畾鍙充晶甯冨眬鐨勫搴︺��
	 */
	private MarginLayoutParams rightLayoutParams;

	/**
	 * 鐢ㄤ簬璁＄畻鎵嬫寚婊戝姩鐨勯�熷害銆�
	 */
	private VelocityTracker mVelocityTracker;

	/**
	 * 閲嶅啓SlidingLayout鐨勬瀯閫犲嚱鏁帮紝鍏朵腑鑾峰彇浜嗗睆骞曠殑瀹藉害銆�
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlidingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	/**
	 * 缁戝畾鐩戝惉渚ф粦浜嬩欢鐨刅iew锛屽嵆鍦ㄧ粦瀹氱殑View杩涜婊戝姩鎵嶅彲浠ユ樉绀哄拰闅愯棌宸︿晶甯冨眬銆�
	 * 
	 * @param bindView
	 *            闇�瑕佺粦瀹氱殑View瀵硅薄銆�
	 */
	public void setScrollEvent(View bindView) {
		mBindView = bindView;
		mBindView.setOnTouchListener(this);
	}

	/**
	 * 灏嗗睆骞曟粴鍔ㄥ埌宸︿晶甯冨眬鐣岄潰锛屾粴鍔ㄩ�熷害璁惧畾涓�30.
	 */
	public void scrollToLeftLayout() {
		new ScrollTask().execute(-10000);
	}

	/**
	 * 灏嗗睆骞曟粴鍔ㄥ埌鍙充晶甯冨眬鐣岄潰锛屾粴鍔ㄩ�熷害璁惧畾涓�-30.
	 */
	public void scrollToRightLayout() {
		new ScrollTask().execute(10000);
	}

	/**
	 * 宸︿晶甯冨眬鏄惁瀹屽叏鏄剧ず鍑烘潵锛屾垨瀹屽叏闅愯棌锛屾粦鍔ㄨ繃绋嬩腑姝ゅ�兼棤鏁堛��
	 * 
	 * @return 宸︿晶甯冨眬瀹屽叏鏄剧ず杩斿洖true锛屽畬鍏ㄩ殣钘忚繑鍥瀎alse銆�
	 */
	public boolean isLeftLayoutVisible() {
		return isLeftLayoutVisible;
	}

	/**
	 * 鍦╫nLayout涓噸鏂拌瀹氬乏渚у竷灞�鍜屽彸渚у竷灞�鐨勫弬鏁般��
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			// 鑾峰彇宸︿晶甯冨眬瀵硅薄
			leftLayout = getChildAt(0);
			leftLayoutParams = (MarginLayoutParams) leftLayout.getLayoutParams();
			rightEdge = -leftLayoutParams.width;
			// 鑾峰彇鍙充晶甯冨眬瀵硅薄
			rightLayout = getChildAt(1);
			rightLayoutParams = (MarginLayoutParams) rightLayout.getLayoutParams();
			rightLayoutParams.width = screenWidth;
			rightLayout.setLayoutParams(rightLayoutParams);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		createVelocityTracker(event);
		if (leftLayout.getVisibility() != View.VISIBLE) {
			leftLayout.setVisibility(View.VISIBLE);
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 鎵嬫寚鎸変笅鏃讹紝璁板綍鎸変笅鏃剁殑妯潗鏍�
			xDown = event.getRawX();
			yDown = event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			// 鎵嬫寚绉诲姩鏃讹紝瀵规瘮鎸変笅鏃剁殑妯潗鏍囷紝璁＄畻鍑虹Щ鍔ㄧ殑璺濈锛屾潵璋冩暣鍙充晶甯冨眬鐨刲eftMargin鍊硷紝浠庤�屾樉绀哄拰闅愯棌宸︿晶甯冨眬
			xMove = event.getRawX();
			yMove = event.getRawY();
			int moveDistanceX = (int) (xMove - xDown);
			int distanceY = (int) (yMove - yDown);
			if (!isLeftLayoutVisible && moveDistanceX >= touchSlop
					&& (isSliding || Math.abs(distanceY) <= touchSlop)) {
				isSliding = true;
				rightLayoutParams.rightMargin = -moveDistanceX;
				if (rightLayoutParams.rightMargin > leftEdge) {
					rightLayoutParams.rightMargin = leftEdge;
				}
				rightLayout.setLayoutParams(rightLayoutParams);
			}
			if (isLeftLayoutVisible && -moveDistanceX >= touchSlop) {
				isSliding = true;
				rightLayoutParams.rightMargin = rightEdge - moveDistanceX;
				if (rightLayoutParams.rightMargin < rightEdge) {
					rightLayoutParams.rightMargin = rightEdge;
				}
				rightLayout.setLayoutParams(rightLayoutParams);
			}
			break;
		case MotionEvent.ACTION_UP:
			xUp = event.getRawX();
			int upDistanceX = (int) (xUp - xDown);
			if (isSliding) {
				// 鎵嬫寚鎶捣鏃讹紝杩涜鍒ゆ柇褰撳墠鎵嬪娍鐨勬剰鍥撅紝浠庤�屽喅瀹氭槸婊氬姩鍒板乏渚у竷灞�锛岃繕鏄粴鍔ㄥ埌鍙充晶甯冨眬
				if (wantToShowLeftLayout()) {
					if (shouldScrollToLeftLayout()) {
						scrollToLeftLayout();
					} else {
						scrollToRightLayout();
					}
				} else if (wantToShowRightLayout()) {
					if (shouldScrollToRightLayout()) {
						scrollToRightLayout();
					} else {
						scrollToLeftLayout();
					}
				}
			} else if (upDistanceX < touchSlop && isLeftLayoutVisible) {
				scrollToRightLayout();
			}
			recycleVelocityTracker();
			break;
		}
		if (v.isEnabled()) {
			if (isSliding) {
				unFocusBindView();
				return true;
			}
			if (isLeftLayoutVisible) {
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * 鍒ゆ柇褰撳墠鎵嬪娍鐨勬剰鍥炬槸涓嶆槸鎯虫樉绀哄彸渚у竷灞�銆傚鏋滄墜鎸囩Щ鍔ㄧ殑璺濈鏄礋鏁帮紝涓斿綋鍓嶅乏渚у竷灞�鏄彲瑙佺殑锛屽垯璁や负褰撳墠鎵嬪娍鏄兂瑕佹樉绀哄彸渚у竷灞�銆�
	 * 
	 * @return 褰撳墠鎵嬪娍鎯虫樉绀哄彸渚у竷灞�杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
	 */
	private boolean wantToShowRightLayout() {
		return xUp - xDown < 0 && isLeftLayoutVisible;
	}

	/**
	 * 鍒ゆ柇褰撳墠鎵嬪娍鐨勬剰鍥炬槸涓嶆槸鎯虫樉绀哄乏渚у竷灞�銆傚鏋滄墜鎸囩Щ鍔ㄧ殑璺濈鏄鏁帮紝涓斿綋鍓嶅乏渚у竷灞�鏄笉鍙鐨勶紝鍒欒涓哄綋鍓嶆墜鍔挎槸鎯宠鏄剧ず宸︿晶甯冨眬銆�
	 * 
	 * @return 褰撳墠鎵嬪娍鎯虫樉绀哄乏渚у竷灞�杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
	 */
	private boolean wantToShowLeftLayout() {
		return xUp - xDown > 0 && !isLeftLayoutVisible;
	}

	/**
	 * 鍒ゆ柇鏄惁搴旇婊氬姩灏嗗乏渚у竷灞�灞曠ず鍑烘潵銆傚鏋滄墜鎸囩Щ鍔ㄨ窛绂诲ぇ浜庡睆骞曠殑1/2锛屾垨鑰呮墜鎸囩Щ鍔ㄩ�熷害澶т簬SNAP_VELOCITY锛�
	 * 灏辫涓哄簲璇ユ粴鍔ㄥ皢宸︿晶甯冨眬灞曠ず鍑烘潵銆�
	 * 
	 * @return 濡傛灉搴旇婊氬姩灏嗗乏渚у竷灞�灞曠ず鍑烘潵杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
	 */
	private boolean shouldScrollToLeftLayout() {
		return xUp - xDown > leftLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * 鍒ゆ柇鏄惁搴旇婊氬姩灏嗗彸渚у竷灞�灞曠ず鍑烘潵銆傚鏋滄墜鎸囩Щ鍔ㄨ窛绂诲姞涓妉eftLayoutPadding澶т簬灞忓箷鐨�1/2锛�
	 * 鎴栬�呮墜鎸囩Щ鍔ㄩ�熷害澶т簬SNAP_VELOCITY锛� 灏辫涓哄簲璇ユ粴鍔ㄥ皢鍙充晶甯冨眬灞曠ず鍑烘潵銆�
	 * 
	 * @return 濡傛灉搴旇婊氬姩灏嗗彸渚у竷灞�灞曠ず鍑烘潵杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
	 */
	private boolean shouldScrollToRightLayout() {
		return xDown - xUp > leftLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * 鍒涘缓VelocityTracker瀵硅薄锛屽苟灏嗚Е鎽镐簨浠跺姞鍏ュ埌VelocityTracker褰撲腑銆�
	 * 
	 * @param event
	 *            鍙充晶甯冨眬鐩戝惉鎺т欢鐨勬粦鍔ㄤ簨浠�
	 */
	private void createVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	/**
	 * 鑾峰彇鎵嬫寚鍦ㄥ彸渚у竷灞�鐨勭洃鍚琕iew涓婄殑婊戝姩閫熷害銆�
	 * 
	 * @return 婊戝姩閫熷害锛屼互姣忕閽熺Щ鍔ㄤ簡澶氬皯鍍忕礌鍊间负鍗曚綅銆�
	 */
	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	/**
	 * 鍥炴敹VelocityTracker瀵硅薄銆�
	 */
	private void recycleVelocityTracker() {
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	/**
	 * 浣跨敤鍙互鑾峰緱鐒︾偣鐨勬帶浠跺湪婊戝姩鐨勬椂鍊欏け鍘荤劍鐐广��
	 */
	private void unFocusBindView() {
		if (mBindView != null) {
			mBindView.setPressed(false);
			mBindView.setFocusable(false);
			mBindView.setFocusableInTouchMode(false);
		}
	}

	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... speed) {
			int rightMargin = rightLayoutParams.rightMargin;
			// 鏍规嵁浼犲叆鐨勯�熷害鏉ユ粴鍔ㄧ晫闈紝褰撴粴鍔ㄥ埌杈惧乏杈圭晫鎴栧彸杈圭晫鏃讹紝璺冲嚭寰幆銆�
			while (true) {
				rightMargin = rightMargin + speed[0];
				if (rightMargin < rightEdge) {
					rightMargin = rightEdge;
					break;
				}
				if (rightMargin > leftEdge) {
					rightMargin = leftEdge;
					break;
				}
				publishProgress(rightMargin);
				// 涓轰簡瑕佹湁婊氬姩鏁堟灉浜х敓锛屾瘡娆″惊鐜娇绾跨▼鐫＄湢20姣锛岃繖鏍疯倝鐪兼墠鑳藉鐪嬪埌婊氬姩鍔ㄧ敾銆�
				sleep(15);
			}
			if (speed[0] > 0) {
				isLeftLayoutVisible = false;
			} else {
				isLeftLayoutVisible = true;
			}
			isSliding = false;
			return rightMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... rightMargin) {
			rightLayoutParams.rightMargin = rightMargin[0];
			rightLayout.setLayoutParams(rightLayoutParams);
			unFocusBindView();
		}

		@Override
		protected void onPostExecute(Integer rightMargin) {
			rightLayoutParams.rightMargin = rightMargin;
			rightLayout.setLayoutParams(rightLayoutParams);
		}
	}

	/**
	 * 浣垮綋鍓嶇嚎绋嬬潯鐪犳寚瀹氱殑姣鏁般��
	 * 
	 * @param millis
	 *            鎸囧畾褰撳墠绾跨▼鐫＄湢澶氫箙锛屼互姣涓哄崟浣�
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//package com.example.mr_time;
//
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//
//
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.VelocityTracker;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.view.View.OnTouchListener;
//import android.view.ViewGroup.MarginLayoutParams;
//import android.view.WindowManager;
//import android.widget.RelativeLayout;
//
//public class SlidingLayout extends RelativeLayout implements OnTouchListener {
//
//	/**
//	 * 滚动显示和隐藏MENU时候，手动滑动需要达到的速度
//	 */
//	public static final int SNAP_VELOCITY = 200;
//
//	/**
//	 * 
//	 */
//	private int screenWidth;
//
//	/**
//	 * menu最多能滑到的左边缘
//	 */
//	private int leftEdge = 0;
//
//	/**
//	 *  menu最多能滑到的右边缘
//	 */
//	private int rightEdge = 0;
//
//	/**
//	 * 触发移动事件的最短距离，如果小于这个距离就不触发移动控件
//	 */
//	private int touchSlop;
//
//	/**
//	 * 记录手指按下时的横坐标
//	 */
//	private float xDown;
//
//	/**
//	 * 记录手指按下时的纵坐标
//	 */
//	private float yDown;
//
//	/**
//	 * 记录手指移动时的横坐标
//	 */
//	private float xMove;
//
//	/**
//	 * 记录手指移动时的纵坐标
//	 */
//	private float yMove;
//
//	/**
//	 * 记录手指抬起时的横坐标
//	 */
//	private float xUp;
//
//	/**
//	 * MENU当前显示还是隐藏，只有完全显示或者隐藏MENU时候才会更改此值
//	 */
//	private boolean isLeftLayoutVisible;
//
//	/**
//	 * 正在滑动
//	 */
//	private boolean isSliding;
//
//	/**
//	 * 宸︿晶甯冨眬瀵硅薄銆�
//	 */
//	private View leftLayout;
//
//	/**
//	 * 鍙充晶甯冨眬瀵硅薄銆�
//	 */
//	private View rightLayout;
//
//	/**
//	 * 鐢ㄤ簬鐩戝惉渚ф粦浜嬩欢鐨刅iew銆�
//	 */
//	private View mBindView;
//
//	/**
//	 * 宸︿晶甯冨眬鐨勫弬鏁帮紝閫氳繃姝ゅ弬鏁版潵閲嶆柊纭畾宸︿晶甯冨眬鐨勫搴︼紝浠ュ強鏇存敼leftMargin鐨勫�笺��
//	 */
//	private MarginLayoutParams leftLayoutParams;
//
//	/**
//	 * 鍙充晶甯冨眬鐨勫弬鏁帮紝閫氳繃姝ゅ弬鏁版潵閲嶆柊纭畾鍙充晶甯冨眬鐨勫搴︺��
//	 */
//	private MarginLayoutParams rightLayoutParams;
//
//	/**
//	 * 用于计算手指滑动的速度
//	 */
//	private VelocityTracker mVelocityTracker;
//
//	/**
//	 * 閲嶅啓SlidingLayout鐨勬瀯閫犲嚱鏁帮紝鍏朵腑鑾峰彇浜嗗睆骞曠殑瀹藉害銆�
//	 * 
//	 * @param context
//	 * @param attrs
//	 */
//	public SlidingLayout(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//		screenWidth = wm.getDefaultDisplay().getWidth();
//		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//	}
//
//	/**
//	 * 缁戝畾鐩戝惉渚ф粦浜嬩欢鐨刅iew锛屽嵆鍦ㄧ粦瀹氱殑View杩涜婊戝姩鎵嶅彲浠ユ樉绀哄拰闅愯棌宸︿晶甯冨眬銆�
//	 * 
//	 * @param bindView
//	 *            闇�瑕佺粦瀹氱殑View瀵硅薄銆�
//	 */
//	public void setScrollEvent(View bindView) {
//		mBindView = bindView;
//		mBindView.setOnTouchListener(this);
//	}
//
//	/**
//	 * 灏嗗睆骞曟粴鍔ㄥ埌宸︿晶甯冨眬鐣岄潰锛屾粴鍔ㄩ�熷害璁惧畾涓�30.
//	 */
//	public void scrollToLeftLayout() {
//		new ScrollTask().execute(-30);
//	}
//
//	/**
//	 * 灏嗗睆骞曟粴鍔ㄥ埌鍙充晶甯冨眬鐣岄潰锛屾粴鍔ㄩ�熷害璁惧畾涓�-30.
//	 */
//	public void scrollToRightLayout() {
//		new ScrollTask().execute(30);
//	}
//
//	/**
//	 * 宸︿晶甯冨眬鏄惁瀹屽叏鏄剧ず鍑烘潵锛屾垨瀹屽叏闅愯棌锛屾粦鍔ㄨ繃绋嬩腑姝ゅ�兼棤鏁堛��
//	 * 
//	 * @return 宸︿晶甯冨眬瀹屽叏鏄剧ず杩斿洖true锛屽畬鍏ㄩ殣钘忚繑鍥瀎alse銆�
//	 */
//	public boolean isLeftLayoutVisible() {
//		return isLeftLayoutVisible;
//	}
//
//	/**
//	 * 鍦╫nLayout涓噸鏂拌瀹氬乏渚у竷灞�鍜屽彸渚у竷灞�鐨勫弬鏁般��
//	 */
//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		super.onLayout(changed, l, t, r, b);
//		if (changed) {
//			leftLayout = getChildAt(0);
//			leftLayoutParams = (MarginLayoutParams) leftLayout.getLayoutParams();
//			rightEdge = -leftLayoutParams.width;
//			// 鑾峰彇鍙充晶甯冨眬瀵硅薄
//			rightLayout = getChildAt(1);
//			rightLayoutParams = (MarginLayoutParams) rightLayout.getLayoutParams();
//			rightLayoutParams.width = screenWidth;
//			rightLayout.setLayoutParams(rightLayoutParams);
//		}
//	}
//
//	private View getChildByName(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//		createVelocityTracker(event);
//		if (leftLayout.getVisibility() != View.VISIBLE) {
//			leftLayout.setVisibility(View.VISIBLE);
//		}
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			// 手指按下时记录横纵坐标
//			xDown = event.getRawX();
//			yDown = event.getRawY();
//			break;
//		case MotionEvent.ACTION_MOVE:
//			// 手指移动时。对比按下时的横纵坐标，计算出移动的距离，来调整menu的leftmargin，从而显示和隐藏menu
//			xMove = event.getRawX();
//			yMove = event.getRawY();
//			int moveDistanceX = (int) (xMove - xDown);
//			int distanceY = (int) (yMove - yDown);
//			if (!isLeftLayoutVisible && moveDistanceX >= touchSlop
//					&& (isSliding || Math.abs(distanceY) <= touchSlop)) {//如果menu不可见、手指移动距离大于最小距离、(正在滑动或者竖直上移动距离小于最小距离)
//				isSliding = true;
//				rightLayoutParams.rightMargin = -moveDistanceX;
//				if (rightLayoutParams.rightMargin > leftEdge) {
//					rightLayoutParams.rightMargin = leftEdge;
//				}
//				rightLayout.setLayoutParams(rightLayoutParams);
//			}
//			if (isLeftLayoutVisible && -moveDistanceX >= touchSlop) {
//				isSliding = true;
//				rightLayoutParams.rightMargin = rightEdge - moveDistanceX;
//				if (rightLayoutParams.rightMargin < rightEdge) {
//					rightLayoutParams.rightMargin = rightEdge;
//				}
//				rightLayout.setLayoutParams(rightLayoutParams);
//			}
//			break;
//		case MotionEvent.ACTION_UP:
//			//手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面还是滚动到content界面
//			xUp = event.getRawX();
//			int upDistanceX = (int) (xUp - xDown);
//			if (isSliding) {
//				
//				if (wantToShowLeftLayout()) {
//					if (shouldScrollToLeftLayout()) {
//						scrollToLeftLayout();
//					} else {
//						scrollToRightLayout();
//					}
//				} else if (wantToShowRightLayout()){
//					if (shouldScrollToRightLayout()) {
//						scrollToRightLayout();
//					} else {
//						scrollToLeftLayout();
//					}
//				}
//			} else if (upDistanceX < touchSlop && isLeftLayoutVisible) {
//				scrollToRightLayout();
//			}
//			recycleVelocityTracker();
//			break;
//		}
//		if (v.isEnabled()) {
//			if (isSliding) {
//				unFocusBindView();
//				return true;
//			}
//			if (isLeftLayoutVisible) {
//				return true;
//			}
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * 鍒ゆ柇褰撳墠鎵嬪娍鐨勬剰鍥炬槸涓嶆槸鎯虫樉绀哄彸渚у竷灞�銆傚鏋滄墜鎸囩Щ鍔ㄧ殑璺濈鏄礋鏁帮紝涓斿綋鍓嶅乏渚у竷灞�鏄彲瑙佺殑锛屽垯璁や负褰撳墠鎵嬪娍鏄兂瑕佹樉绀哄彸渚у竷灞�銆�
//	 * 
//	 * @return 褰撳墠鎵嬪娍鎯虫樉绀哄彸渚у竷灞�杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
//	 */
//	private boolean wantToShowRightLayout() {
//		return xUp - xDown < 0 && isLeftLayoutVisible;
//	}
//
//	/**
//	 * 鍒ゆ柇褰撳墠鎵嬪娍鐨勬剰鍥炬槸涓嶆槸鎯虫樉绀哄乏渚у竷灞�銆傚鏋滄墜鎸囩Щ鍔ㄧ殑璺濈鏄鏁帮紝涓斿綋鍓嶅乏渚у竷灞�鏄笉鍙鐨勶紝鍒欒涓哄綋鍓嶆墜鍔挎槸鎯宠鏄剧ず宸︿晶甯冨眬銆�
//	 * 
//	 * @return 褰撳墠鎵嬪娍鎯虫樉绀哄乏渚у竷灞�杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
//	 */
//	private boolean wantToShowLeftLayout() {
//		return xUp - xDown > 0 && !isLeftLayoutVisible;
//	}
//
//	/**
//	 * 鍒ゆ柇鏄惁搴旇婊氬姩灏嗗乏渚у竷灞�灞曠ず鍑烘潵銆傚鏋滄墜鎸囩Щ鍔ㄨ窛绂诲ぇ浜庡睆骞曠殑1/2锛屾垨鑰呮墜鎸囩Щ鍔ㄩ�熷害澶т簬SNAP_VELOCITY锛�
//	 * 灏辫涓哄簲璇ユ粴鍔ㄥ皢宸︿晶甯冨眬灞曠ず鍑烘潵銆�
//	 * 
//	 * @return 濡傛灉搴旇婊氬姩灏嗗乏渚у竷灞�灞曠ず鍑烘潵杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
//	 */
//	private boolean shouldScrollToLeftLayout() {
//		return xUp - xDown > leftLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
//	}
//
//	/**
//	 * 鍒ゆ柇鏄惁搴旇婊氬姩灏嗗彸渚у竷灞�灞曠ず鍑烘潵銆傚鏋滄墜鎸囩Щ鍔ㄨ窛绂诲姞涓妉eftLayoutPadding澶т簬灞忓箷鐨�1/2锛�
//	 * 鎴栬�呮墜鎸囩Щ鍔ㄩ�熷害澶т簬SNAP_VELOCITY锛� 灏辫涓哄簲璇ユ粴鍔ㄥ皢鍙充晶甯冨眬灞曠ず鍑烘潵銆�
//	 * 
//	 * @return 濡傛灉搴旇婊氬姩灏嗗彸渚у竷灞�灞曠ず鍑烘潵杩斿洖true锛屽惁鍒欒繑鍥瀎alse銆�
//	 */
//	private boolean shouldScrollToRightLayout() {
//		return xDown - xUp > leftLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
//	}
//
//	/**
//	 * 鍒涘缓VelocityTracker瀵硅薄锛屽苟灏嗚Е鎽镐簨浠跺姞鍏ュ埌VelocityTracker褰撲腑銆�
//	 * 
//	 * @param event
//	 *            鍙充晶甯冨眬鐩戝惉鎺т欢鐨勬粦鍔ㄤ簨浠�
//	 */
//	private void createVelocityTracker(MotionEvent event) {
//		if (mVelocityTracker == null) {
//			mVelocityTracker = VelocityTracker.obtain();
//		}
//		mVelocityTracker.addMovement(event);
//	}
//
//	/**
//	 * 鑾峰彇鎵嬫寚鍦ㄥ彸渚у竷灞�鐨勭洃鍚琕iew涓婄殑婊戝姩閫熷害銆�
//	 * 
//	 * @return 婊戝姩閫熷害锛屼互姣忕閽熺Щ鍔ㄤ簡澶氬皯鍍忕礌鍊间负鍗曚綅銆�
//	 */
//	private int getScrollVelocity() {
//		mVelocityTracker.computeCurrentVelocity(1000);
//		int velocity = (int) mVelocityTracker.getXVelocity();
//		return Math.abs(velocity);
//	}
//
//	/**
//	 * 鍥炴敹VelocityTracker瀵硅薄銆�
//	 */
//	private void recycleVelocityTracker() {
//		mVelocityTracker.recycle();
//		mVelocityTracker = null;
//	}
//
//	/**
//	 * 浣跨敤鍙互鑾峰緱鐒︾偣鐨勬帶浠跺湪婊戝姩鐨勬椂鍊欏け鍘荤劍鐐广��
//	 */
//	private void unFocusBindView() {
//		if (mBindView != null) {
//			mBindView.setPressed(false);
//			mBindView.setFocusable(false);
//			mBindView.setFocusableInTouchMode(false);
//		}
//	}
//
//	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {
//
//		@Override
//		protected Integer doInBackground(Integer... speed) {
//			int rightMargin = rightLayoutParams.rightMargin;
//			// 鏍规嵁浼犲叆鐨勯�熷害鏉ユ粴鍔ㄧ晫闈紝褰撴粴鍔ㄥ埌杈惧乏杈圭晫鎴栧彸杈圭晫鏃讹紝璺冲嚭寰幆銆�
//			while (true) {
//				rightMargin = rightMargin + speed[0];
//				if (rightMargin < rightEdge) {
//					rightMargin = rightEdge;
//					break;
//				}
//				if (rightMargin > leftEdge) {
//					rightMargin = leftEdge;
//					break;
//				}
//				publishProgress(rightMargin);
//				// 涓轰簡瑕佹湁婊氬姩鏁堟灉浜х敓锛屾瘡娆″惊鐜娇绾跨▼鐫＄湢20姣锛岃繖鏍疯倝鐪兼墠鑳藉鐪嬪埌婊氬姩鍔ㄧ敾銆�
//				sleep(15);
//			}
//			if (speed[0] > 0) {
//				isLeftLayoutVisible = false;
//			} else {
//				isLeftLayoutVisible = true;
//			}
//			isSliding = false;
//			return rightMargin;
//		}
//
//		@Override
//		protected void onProgressUpdate(Integer... rightMargin) {
//			rightLayoutParams.rightMargin = rightMargin[0];
//			rightLayout.setLayoutParams(rightLayoutParams);
//			unFocusBindView();
//		}
//
//		@Override
//		protected void onPostExecute(Integer rightMargin) {
//			rightLayoutParams.rightMargin = rightMargin;
//			rightLayout.setLayoutParams(rightLayoutParams);
//		}
//	}
//
//	/**
//	 * 浣垮綋鍓嶇嚎绋嬬潯鐪犳寚瀹氱殑姣鏁般��
//	 * 
//	 * @param millis
//	 *            鎸囧畾褰撳墠绾跨▼鐫＄湢澶氫箙锛屼互姣涓哄崟浣�
//	 */
//	private void sleep(long millis) {
//		try {
//			Thread.sleep(millis);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//}
//
