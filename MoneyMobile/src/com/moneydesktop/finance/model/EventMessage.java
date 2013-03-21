package com.moneydesktop.finance.model;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.moneydesktop.finance.data.Enums.FragmentType;
import com.moneydesktop.finance.data.Enums.LockType;
import com.moneydesktop.finance.data.Enums.NavDirection;
import com.moneydesktop.finance.database.AccountType;
import com.moneydesktop.finance.database.Bank;
import com.moneydesktop.finance.database.PowerQuery;
import com.moneydesktop.finance.views.AnchorView;
import com.moneydesktop.finance.views.BarView;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventMessage {
    
    public class AnchorChangeEvent extends EventMessage {
        private AnchorView mLeft;
        private AnchorView mRight;
        
        public AnchorChangeEvent(AnchorView left, AnchorView right) {
            mLeft = left;
            mRight = right;
        }
        
        public AnchorView getLeft() {
            return mLeft;
        }
        
        public AnchorView getRight() {
            return mRight;
        }
    }
    
	public class AuthEvent extends EventMessage {}
	
	public class UpdateSpecificBankStatus extends EventMessage {	
		private Bank mBank;
		
		public UpdateSpecificBankStatus(Bank bank) {
			this.mBank = bank;
		}
		
		public Bank getBank() {
			return mBank;
		}
	}
	
	public class MfaQuestionsRecieved extends EventMessage {	
		private List<String> mList;
		
		public MfaQuestionsRecieved(List<String> mQuestionLabels) {
			this.mList = mQuestionLabels;
		}
		
		public List<String> getQuestions() {
			return mList;
		}
	}
	
	public class SaveInstitutionFinished extends EventMessage {
		private JSONObject object;
	
		public SaveInstitutionFinished(JSONObject jsonResponse) {
			this.object = jsonResponse;
		}
		
		public JSONObject getJsonResponse() {
			return object;
		}
	}
	
	
	public class UpdateCredentialsFinished extends EventMessage {
		private JSONObject object;
	
		public UpdateCredentialsFinished(JSONObject jsonResponse) {
			this.object = jsonResponse;
		}
		
		public JSONObject getJsonResponse() {
			return object;
		}
	}
	
	public class GetLogonCredentialsFinished extends EventMessage {
		private boolean isAddedForFirstTime = false;
		private List<String> logonTxtLabels;
		
	       public GetLogonCredentialsFinished(boolean isAccountAddedForFirstTime, List<String> loginLabels) {
	           this.isAddedForFirstTime = isAccountAddedForFirstTime;
	           this.logonTxtLabels = loginLabels;
	       }
	       
	       public boolean isAddedForFistTime() {
	           return isAddedForFirstTime;
	       }
	       
	       public List<String> getLogonLabels() {
	           return logonTxtLabels;
	       }
		
	}
	
	public class BackEvent extends EventMessage {}
	public class ChartImageEvent extends EventMessage {
		
		private Bitmap image;
		
		public ChartImageEvent(Bitmap image) {
			this.image = image;
		}
		
		public Bitmap getImage() {
			return image;
		}
	}
    public class DatabaseSaveEvent extends EventMessage {
    	
    	private List<Class<?>> mClasses = new ArrayList<Class<?>>();
    	
    	public DatabaseSaveEvent(List<Class<?>> classes) {
    		mClasses = classes;
    	}
    	
    	public List<Class<?>> getChangedClassesList() {
    		return mClasses;
    	}
    	
    	public boolean didDatabaseChange() {
    		return mClasses.size() > 0;
    	}
    }
	public class FeedbackEvent extends EventMessage {}
	
	public class FilterEvent extends EventMessage {
	    
	    private PowerQuery mQueries;
	    
	    public FilterEvent(PowerQuery queries) {
	        mQueries = queries;
	    }
	    
	    public PowerQuery getQuery() {
	        return mQueries;
	    }
	}
	
	public class LoginEvent extends EventMessage {}
	public class LogoutEvent extends EventMessage {}
	
	public class LockEvent extends EventMessage {
		
		protected LockType mType;

		public LockEvent(LockType type) {
			this.mType = type;
		}
		
		public LockType getType() {
			return mType;
		}
	}
	
	public class MenuEvent extends EventMessage {
	    
	    private int mGroupPosition, mChildPosition;
	    private FragmentType mFragmentType;
	    
	    public MenuEvent(int groupPosition, int childPosition, FragmentType fragmentType) {
	    	mGroupPosition = groupPosition;
	    	mChildPosition = childPosition;
	    	mFragmentType = fragmentType;
	    }
	    
	    public int getGroupPosition() {
	        return mGroupPosition;
	    }
	    
	    public int getChildPosition() {
	        return mChildPosition;
	    }
	    
	    public FragmentType getFragmentType() {
	        return mFragmentType;
	    }

        /**
         * Provides a unique int for the combined group and child position
         *
         * @return the combined id
         */
        public int getAction() {
            return ((mGroupPosition << 8) + mChildPosition);
        }
	}
	
	public class NavigationButtonEvent extends EventMessage {}
	public class NavigationEvent extends EventMessage {
		
		protected Boolean mShowing;
		protected NavDirection mDirection;
		protected Boolean mMovingHome;
		protected Boolean mToggleNavigation;
		protected FragmentType mType;
		
		public Boolean getMovingHome() {
			return mMovingHome;
		}

		public void setMovingHome(Boolean mMovingHome) {
			this.mMovingHome = mMovingHome;
			this.mToggleNavigation = null;
		}

		public NavigationEvent() {
			this.mToggleNavigation = true;
		}
		
		public NavigationEvent(FragmentType type) {
			mType = type;
		}
		
		public NavigationEvent(boolean showing) {
			this.mShowing = showing;
			this.mDirection = null;
		}
		
		public NavigationEvent(NavDirection direction) {
			this.mShowing = null;
			this.mDirection = direction;
		}
		
		public FragmentType getType() {
			return mType;
		}
		
		public Boolean isShowing() {
			return mShowing;
		}
		
		public Boolean getToggleNavigation() {
			return mToggleNavigation;
		}
		
		public NavDirection getDirection() {
			return mDirection;
		}
	}

    public class ParentAnimationEvent extends EventMessage {
        protected Boolean mOutAnimation;
        protected Boolean mFinished;
        protected boolean mIsNav = false;

        public ParentAnimationEvent(boolean outAnimation, boolean finished) {
            mOutAnimation = outAnimation;
            mFinished = finished;
        }
        
        public ParentAnimationEvent(boolean outAnimation, boolean finished, boolean isNav) {
            mOutAnimation = outAnimation;
            mFinished = finished;
            mIsNav = isNav;
        }
        
        public boolean isNavigation() {
            return mIsNav;
        }
        
        public boolean isOutAnimation() {
            return mOutAnimation;
        }
        
        public boolean isFinished() {
            return mFinished;
        }
    }
	
	public class SyncEvent extends EventMessage {
		
		protected boolean mFinished;

		public SyncEvent(boolean finished) {
			this.mFinished = finished;
		}
		
		public boolean isFinished() {
			return mFinished;
		}

		public void setFinished(boolean finished) {
			this.mFinished = finished;
		}
	}
    public class GraphDataZoomEvent extends EventMessage {
        private long mStartDate;
        private int mType;

        public long getDate() {
            return mStartDate;
        }

        public void setDate(long d) {
            mStartDate = d;
        }

        public int getType() {
            return mType;
        }

        public void setType(int type) {
            this.mType = type;
        }

    }

    public class GraphBarTouchEvent extends EventMessage {
        private MotionEvent mMotion;
        private BarView mBar;

        public void setBar(BarView v) {
            mBar = v;
        }

        public BarView getBar() {
            return mBar;
        }
    }

    public class BankStatusUpdateEvent extends EventMessage {
        
        protected Bank mBank;

        public BankStatusUpdateEvent(Bank bank) {
            this.mBank = bank;
        }
        
        public Bank getUpdatedBank() {
            return mBank;
        }

    }
   
   public class RefreshAccountEvent extends EventMessage {
       
       protected Bank mBank;

       public RefreshAccountEvent(Bank bank) {
           this.mBank = bank;
       }
       
       public Bank getRefreshedBank() {
           return mBank;
       }

   }
   
   public class ReloadBannersEvent extends EventMessage {
       public ReloadBannersEvent() {
           super();
       }
   }
   
   public class BankDeletedEvent extends EventMessage {
       protected Bank mBank;

       public BankDeletedEvent(Bank bank) {
           this.mBank = bank;
       }
       
       public Bank getDeletedBank() {
           return mBank;
       }
   }
	
   public class RemoveAccountTypeEvent extends EventMessage {
       protected AccountType mAccountType;

       public RemoveAccountTypeEvent(AccountType accountToBeRemoved) {
           this.mAccountType = accountToBeRemoved;
       }
       
       public AccountType getAccountType() {
           return mAccountType;
       }
   }
   
	public class CheckRemoveBankEvent extends EventMessage {
		protected Bank mBank;
		
		public CheckRemoveBankEvent(Bank bank) {
			this.mBank = bank;
		}
			       
        public Bank getBank() {
            return mBank;
        }
	}
   
	protected HashMap<String, Object> mInfo;
	protected String mMessage;
	
	public HashMap<String, Object> getInfo() {
		return mInfo;
	}
	
	public void setInfo(HashMap<String, Object> info) {
		this.mInfo = info;
	}
	
	public String getMessage() {
		return mMessage;
	}
	
	public void setMessage(String message) {
		this.mMessage = message;
	}
}
