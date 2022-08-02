package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrayerMain extends Fragment implements OnItemClickListener {
    public static final String EXTRA_MESSAGE = "com.id.ccchpad.MESSAGE";
    public static final String EXTRA_MESSAGEE = "com.id.ccchpad.MESSAGE";
    public static final String EXTRA_MESSAGER = "com.id.ccchpad.MESSAGE";
    public static final String EXTRA_MESSAGERR = "com.id.ccchpad.MESSAGE";
    List<String> childList;
    List<String> childLista;
    ImageView cvg;
    RadioButton eng;
    ExpandableListView expListView;
    ExpandableListView expandableList;
    FragmentTransaction frag;
    FragmentManager fragm;
    List<String> groupList;
    List<String> groupLista;
    Map<String, List<String>> laptopCollection;
    Map<String, List<String>> laptopCollectiona;
    TextView ld;
    int myNum = 5000;
    TextView se;
    EditText search;
    ImageView see;
    TextView seed;
    String sele;
    Thread timer;
    RadioGroup ver;
    RelativeLayout ww;
    RadioButton yor;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView autoComplete;
    private ArrayList<Object> childItems = new ArrayList();
    private ArrayList<String> parentItems = new ArrayList();
    private AutoCompleteTextView srch;
    private String user_id;

    public static PrayerMain newInstance(String user_id) {
        PrayerMain fragment = new PrayerMain();
        Bundle args = new Bundle();
        args.putString(user_id, user_id);
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.prayer_expandable, container, false);
        this.fragm = getFragmentManager();
        this.frag = this.fragm.beginTransaction().addToBackStack("f1");
        this.adapter = new ArrayAdapter(getActivity(), 17367043, getResources().getStringArray(R.array.searchtopics));
        this.autoComplete = rootView.findViewById(R.id.search);
        this.autoComplete.setAdapter(this.adapter);
        this.autoComplete.setThreshold(1);

        if (Build.VERSION.SDK_INT >= 26) {
            this.autoComplete.setFocusedByDefault(false);
        }
        this.autoComplete.setFocusableInTouchMode(true);
        this.autoComplete.setOnItemClickListener(this);
        this.se = rootView.findViewById(R.id.se);
        this.expListView = rootView.findViewById(R.id.laptop_list);
        english();
        this.cvg = rootView.findViewById(R.id.seem);
        this.cvg.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(PrayerMain.this.getActivity(), R.anim.alpha));
                Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
                intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
                PrayerMain.this.startActivityForResult(intent, 1234);
            }
        });
        return rootView;
    }

    public void engV(View ve) {
        ve.setBackgroundResource(R.drawable.img1);
        english();
    }

    private void english() {
        createGroupList1();
        createChildList1();
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(getActivity(), this.groupList, this.laptopCollection);
        this.expListView.setAdapter(expListAdapter);
        this.expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @SuppressLint("WrongConstant")
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                PrayerMain.this.getView().findViewById(R.id.se).startAnimation(AnimationUtils.loadAnimation(PrayerMain.this.getActivity(), R.anim.slide_out_left));
                PrayerMain.this.getView().findViewById(R.id.se).setVisibility(8);
                return false;
            }
        });
        this.expListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selected = (String) expListAdapter.getChild(groupPosition, childPosition);
                PrayerMain.this.autoComplete.selectAll();
                Intent b = new Intent(PrayerMain.this.getActivity(), PrayerOutput.class);
                b.putExtra("english", selected);
                PrayerMain.this.startActivity(b);
                PrayerMain.this.getActivity().overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                return true;
            }
        });
    }

    private void createGroupList1() {
        this.groupList = new ArrayList();
        this.groupList.add("POWER MUST CHANGE HANDS PRAYERS\n\n(2006 - 2020)");
        this.groupList.add("PRAYER POINTS FOR MARRIAGE\n\n(----------------**********----------------)");
        this.groupList.add("CONFESSION FOR PREGNANT WOMEN\n\n(----------------**********----------------)");
        this.groupList.add("PRAYER AGAINST WITCHCRAFT'S\n\n(----------------**********----------------)");
        this.groupList.add("PRAYERS FOR DIVINE FAVOUR\n\n(----------------**********----------------)");
        this.groupList.add("BONDAGE-BREAKING PRAYERS\n\n(----------------**********----------------)");
        this.groupList.add("DANGEROUS PRAYERS\n\n(----------------**********----------------)");
        this.groupList.add("DELIVERANCE PRAYER\n\n(----------------**********----------------)");
        this.groupList.add("OTHERS\n\n(----------------**********----------------)");
    }

    private void createChildList1() {
        String[] x1 = new String[]{"SEPTEMBER 2020 - CONNECTING TO THE PASSOVER BLOOD OF JESUS","AUGUST 2020 - SPECIAL POWER MUST CHANGE HANDS PRAYERS WAR AGAINST INFIRMITY","JULY 2020 - DIVINE PROTECTION (4)","JUNE 2020 - DIVINE PROTECTION (3)","MAY 2020 - DIVINE PROTECTION (2)","APRIL 2020 - DIVINE PROTECTION","MARCH 2020 - RE-WRITING YOUR FAMILY HISTORY (3)","DECEMBER 2019 - POWER FOR THE NEXT LEVEL (2)","AUGUST 2019 - MOUNT UP WITH WINGS AS EAGLES (2)","JULY 2019 - MOUNT UP WITH WINGS AS EAGLES","JUNE 2019 - CONNECTING TO THE STORY CHANGER","MAY 2019 - PULLING DOWN STRANGE STRONGHOLD","APRIL 2019 - O HEAVENS, MAKE A WAY FOR ME (2)","MARCH 2019 - O HEAVENS, MAKE A WAY FOR ME","FEBRUARY 2019 - SOARING LIKE THE EAGLE","DECEMBER 2018 - PURSUE, OVERTAKE AND RECOVER (2)","NOVEMBER 2018 - PURSUE, OVERTAKE AND RECOVER","AUGUST 2018 - GLORY RESTORATION","JULY 2018 - CONNECTING TO DIVINE ACCELERATION","JUNE 2018 - CONNECTING TO THE GREAT DELIVERER",
                "MAY 2018 - CONNECTING TO THE GREAT PHYSICIAN","APRIL 2018 - GREAT DELIVERANCE","MARCH 2018 - DISGRACING THE DARK MARKET","FEBRUARY 2018 - I SECURE MY PORTION","DECEMBER 2017 - KILLING GLORY KILLERS","NOVEMBER 2017 - MY HORN SHALL BE EXALTED","AUGUST 2017 - POWER TO MOVE FROM INSULT TO RESULT","JULY 2017 - ANOINTING FOR PROMOTION","JUNE 2017 CONNECTING TO THE GOD OF SIGNS AND WONDERS","MAY 2017 - POWER AGAINST BEWITCHMENT (3)","APRIL 2017 - POWER AGAINST BEWITCHMENT (2)","MARCH 2017-  POWER AGAINST BEWITCHMENT","FEBRUARY 2017 - SHOW ME MY DESTINY LADDER","DECEMBER 2016 - RECEIVING THE ANOINTING OF FIRE","NOVEMBER 2016 - THE GREAT DELIVERANCE","AUGUST 2016 - ARRESTING THE ARRESTERS","JULY 2016 - I PLUG MYSELF INTO THE SOCKET OF REVELATION POWER","JUNE 2016 - ARISE AND SHINE","MAY 2016 - POWER AGAINST WICKED PLANNERS AND ASSOCIATION","APRIL 2016 - BREAKTHROUGH DELIVERANCE","MARCH 2016 - WICKEDNESS MUST EXPIRE","FEBRUARY 2016 - I FIGHT LIKE THE PSALMIST","DECEMBER 2015 - UPROOTING EVIL TREE (2)","NOVEMBER 2015 - UPROOTING EVIL TREE","AUGUST 2015 - THE VIOLENT TAKETH IT BY FORCE","JULY 2015 - THE VIOLENT TAKETH IT BY FORCE","JUNE 2015 - THE VIOLENT TAKETH IT BY FORCE","MAY 2015 - THE VIOLENT TAKETH IT BY FORCE","APRIL 2015 - RECOVERY OF STAR (2)","MARCH 2015 - RECOVERY OF STAR",
                "FEBRUARY 2015 - POWER AGAINST DESTINY ROBBERS","DECEMBER 2014 - THE LORD WILL PERFECT THAT WHICH CONCERNS ME","NOVEMBER 2014 - I PLUG MYSELF INTO THE SOCKET OF DIVINE FAVOUR","AUGUST 2014 - HOLY GHOST IMMUNIZATION","JULY 2014 - O STARS OF HEAVEN, FIGHT FOR ME","JUNE 2014 - TAKING IT BY FORCE (5)","MAY 2014 - TAKING IT BY FORCE (4)","FEBRUARY 2014 - TAKING IT BY FORCE  (1)","APRIL 2014 - TAKING IT BY FORCE (3)","MARCH 2014 - TAKING IT BY FORCE  (2)","DECEMBER 2013 - DIVINE ACCELERATION (3)","NOVEMBER 2013 - DIVINE ACCELERATION (2)","AUGUST 2013 - DESTROYING SATANIC DATABASE FASHIONED AGAINST ME","JULY 2013 - MY STAR MUST SHINE (5)","JUNE 2013 - MY STAR MUST SHINE (4)","MAY 2013 - MY STAR MUST SHINE (3)","APRIL 2013 - MY STAR MUST SHINE (2)","MARCH 2013 - MY STAR MUST SHINE","FEBRUARY 2013 - DELIVERANCE OF THE FAMILY TREE (4)","DECEMBER 2012 - DELIVERANCE OF THE FAMILY TREE (2)","NOVEMBER 2012 - DELIVERANCE OF THE FAMILY TREE (1)","AUGUST 2012 - CONNECTING TO THE GLORY AND THE LIFTER OF MY HEAD","JULY 2012 - CRUSHING DESTINY VANDALS","JUNE 2012 - WICKED POWERS, CRASHLAND","MAY 2012 - O GOD OF POSSIBILITIES, ARISE","APRIL 2012 - THIS CITY SHALL NOT BE MY CALDRON","MARCH 2012 -  MY GOLIATH MUST FALL","FEBRUARY 2012 - MY FATHER, SHOW ME SECRETS","DECEMBER 2011 - DESTINY REVIVAL","SEPTEMBER 2011 - FOURTH MAN IN MY LIFE, APPEAR BY FIRE","AUGUST 2011 - SPEAK THE WORD, IT SHALL NOT STAND","JULY 2011 - SETTINGS THE COVEN ABLAZE","JUNE 2011 - LION OF JUDAH, ROAR, PURSUE AFFLICTION OUT OF MY LIFE","MAY 2011 - LET GOD BE GOD IN MY LIFE",
                "APRIL 2011 - O GOD, ARISE AND CHANGE THE RULES FOR MY SAKE","MARCH 2011 - O VOICE OF GOD, SPEAK FOR ME","FEBRUARY 2011 - CRUSHING STUBBORN OBSTACLES","DECEMBER 2010 - LET THE STARS FIGHT FOR ME","SEPTEMBER 2010 - CRUSHING THE POWER OF THE FOWLER","AUGUST 2010 - CALLING THE WASTERS TO ORDER","JULY 2010 - IT IS MY TIME TO LAUGH","JUNE 2010 - O LORD, LET YOUR MANTLE OF FIRE FALL ON ME","MAY 2010 - RETRENCH SATANIC PRIESTS","APRIL 2010 - BINDING EVIL KINGS AND NOBLES","MARCH 2010 - CRUSHING YOUR GOLIATH","FEBRUARY 2010 - MY FATHER, EMPOWER ME TO SUCCEED","JANUARY 2010 - COMMAND THE YEAR","DECEMBER 2009 - DOORS OF BREAKTHROUGH, EPHPHATHA","SEPTEMBER 2009 - O GOD, ARISE AND PROVOKE FAVOUR ON ME","AUGUST 2009 - POWER FOR THE NEXT LEVEL","JULY 2009 - BREAKING THE LAWS OF DARKNESS","JUNE 2009 - O GOD OF 24-HOUR MIRACLE, ARISE","MAY 2009 - WASTE THE WASTERS","APRIL 2009 -O GOD ARISE AND BREAK MY CHAINS","MARCH 2009 -SILENCING SATANIC PROPHETS","FEBRUARY 2009 - MY STORY MUST CHANGE TO GLORY","JANUARY 2009 - COMMAND THE YEAR","NOVEMBER 2008 - REFRESHING FIRE","OCTOBER 2008 - NO MORE EMBARGO","SEPTEMBER 2008 - MY HORN SHALL BE EXALTED","AUGUST 2008 - POWER AGAINST GRAVE YARD SPIRIT","JULY 2008 - GIVE ME THIS MOUNTAIN","JUNE 2008 - DESTROYING DESTRUCTIVE COVENANTS 2","MAY 2008 - DESTROYING DESTRUCTIVE COVENANTS 1","APRIL 2008 - CRUSHING THE AGENDA OF THE WICKED","MARCH 2008 - REPOSITIONING PRAYERS 2","FEBRUARY 2008 - REPOSITIONING PRAYERS 1","JANUARY 2008 - O LORD, GLORIFY YOUR NAME IN MY LIFE","OCTOBER 2007 - HOLY GHOST FIRE, ENVELOPE MY LIFE","SEPTEMBER 2007 - THOU WILT REVIVE ME","JUNE 2007 - DEALING WITH THE POWER OF THE ASSYRIANS","MAY 2007 - PRAYERS TO CRUSH EVIL CHAINS","FEBRUARY 2007 - EVIL ARROW ASSIGNED BY DAY AND NIGHT, BACKFIRE","SEPTEMBER  2006 - DISGRACING ENCHANTMENTS","AUGUST  2006 - BREAKING THE POWER OF THE STRONGMAN","JULY  2006 - BREAKING THE YOKE OF SATANIC DELAY","JUNE  2006 - DEALING WITH THE POWERS OF THE NIGHT","APRIL 2006 - BEFORE ME, GREAT MOUNTAINS SHALL","MARCH 2006 - LET MY DAYS BEGIN TO SPEAK","FEBRUARY 2006 - MY COVENANT WITH GOD THIS YEAR"};

        String[] x2 = new String[]{"WAR AGAINST MARRIAGE BREAKERS","PRAYER FOR BACHELORS AND SPINSTERS ON MARRIAGE","HOW TO WIN YOUR HUSBAND’S HEART","PRAYERS TO ARREST UNPROFITABLE LATENESS IN MARRIAGE","PRAYERS TO KNOW GOD’S WILL IN MARRIAGE"};
        String[] x3 = new String[]{"DESTROYING ANTI-CONCEPTION PARASITE","PRAYERS FOR DIVINE GUIDANCE AND PROTECTION FOR PREGNANT WOMAN","POWER TO CONCEIVE AND BEAR CHILDREN","POWER TO RETAIN PREGNANCY","PRAYER POINTS FOR BABIES IN THE WOMB","WOMB POLLUTERS","DELIVERANCE OF THE WOMB","THOU ENEMY OF MY CHILDBEARING YOU ARE IN TROUBLE","PRAYER AGAINST MISCARRIAGE","DELIVERANCE FOR THE REPRODUCTIVE ORGAN","PRAYER AND CONFESSION FOR PREGNANT WOMEN","SWALLOWER OF MY CHILDBEARING MUST DIE"};
        String[] x4 = new String[]{"CLOSING DOORS AGAINST WITCHCRAFT","DEALING WITH FOUNDATIONAL WITCHCRAFT POWERS","PRAYERS AGAINST WITCHCRAFT SPONSORED DISEASES","VICTORY BY FIRE OVER WITCHCRAFT VERDICTS","BREAKING THE STRONGHOLD OF WITCHCRAFT","POWER AGAINST MARINE WITCHCRAFT","PRAYERS TO DEFEAT ANTI-CONCEPTION WITCHCRAFT"};

        String[] x5 = new String[]{"COMPETITION SUCCESS","BREAKTHROUGH PRAYER POINTS","MY TIME OF SURPLUS FAVOR (2)","PRAYERS FOR DIVINE WISDOM","PRAYERS AGAINST FINANCIAL CRISES","PRAYERS AGAINST POVERTY","PRAYERS FOR DIVINE ELEVATION","PRAYERS FOR DIVINE HEALING","PRAYERS FOR DIVINE WISDOM","PRAYERS FOR IMPROVED SALES AND SERVICES","PRAYERS FOR SPIRITUAL GROWTH","POWER TO OBTAIN PROFITABLE EMPLOYMENT","PRAYERS FOR SUCCESS AND BREAKTHROUGHS IN BUSINESS","PRAYERS FOR UNCOMMON BREAKTHROUGHS","PRAYERS OF RESTORATION","DIVINE POWER OVER EARTHLY PROBLEMS","DIVINE HEALING","THE BATTLE CRY FOR BREAKTHROUGHS","POWER TO BE CONNECTED WITH GREAT MEN IN HIGH PLACES","I PLUG MYSELF INTO THE SOCKET OF DIVINE FAVOUR"};

        String[] x6 = new String[]{"ATTACKING THE ENEMY OF YOUR CALLING","DEALING WITH ANTI-PROMOTION WITCHCRAFT","DEALING WITH SECRET DREAM COVENANTS","DEVASTATING THE KINGDOM OF MARINE WITCHCRAFT","I AM FOR BLESSINGS AND NOT FOR CURSES","STAGNANCY MUST DIE","PRAYER AGAINST INHERITED FOUNDATIONAL BATTLE","DELIVERANCE FROM EVIL INHERITANCE","DELIVERANCE FROM THE BONDAGE OF MARINE SPIRITS","BREAKING FREE FROM THE ROOM OF DARKNESS","BREAKING THE YOKE OF EVIL BLOOD CRY AGAINST MY FRUITFULNESS","BREAKING FREE FROM THE GRIP OF THE EVIL POWERS OF MY FATHER’S HOUSE","BREAKING EVIL FOUNDATIONAL YOKES","BREAKING THE LAWS OF DARKNESS","BREAKING THE POWER OF THE STRONGMAN","BREAKING THE YOKE OF DESTINY SUICIDE","BREAKING ANCESTRAL EVIL HOLD","BREAKING THE STUBBORN CURSES"};

        String[] x7 = new String[]{"DEATH TRAP","SPEAK DESTRUCTION UNTO PROBLEM MOUNTAINS","DESTROYING ANTI-PROSPERITY FORCES","DEFEATING ANTI-HARVEST FORCES","DEFEATING THE PATTERN OF EVIL HOUSE, STRANGE TENANTS AND EVIL LANDLORD"};

        String[] x8 = new String[]{"SPIRITUAL FUMIGATION","SPECIAL PRAYERS FOR MUSLIM CONVERTS","DEEP DELIVERANCE FROM THE POWER OF NATIVITY AND GREAT HOUSE OF MY FATHERS HOUSE","DEALING WITH THE SPIRIT OF THE DOG","DREAM SURGERY 1","DREAM SURGERY 2","FLUSHING OUT EVIL DEPOSIT IN THE BODY","DEALING WITH WATER SPIRITS AND EVIL MARRIAGES","RE-WRITING YOUR FAMILY HISTORY","ANTI-INFIRMITY MISSILES","DELIVERANCE FROM FOUNDATIONAL BONDAGE","PRAYER FOR FIRST BORN DELIVERANCE","DELIVERANCE OF THE EAGLE","DELIVERANCE OF THE PREY OF THE MIGHTY (1)","DISGRACING THE APOSTLES OF SATAN","PRAYER ON DELIVERANCE OF THE MIND","FAMILY DELIVERANCE","UPROOTING EVIL TREE 1","UPROOTING EVIL TREE 2","DEEP DELIVERANCE 1 RECOVERY OF STAR","DEEP DELIVERANCE 2 RECOVERY OF STAR 2","DEEP DELIVERANCE FOR THE VIOLENT TAKETH IT BY FORCE 2","DEEP DELIVERANCE 3 THE VIOLENT TAKETH IT BY FORCE 2","O LORD, VISIT ME AND DESTROY EVERY WORK OF THE ENEMY",};

        String[] x9 = new String[]{"COME OUT AND ENTER NO MORE","POWER AGAINST SPIRITUAL BURIAL","PRAYER TO DESTROY BED WETTINGS","PRAYERS FOR PASTORAL SUCCESS","POWER AGAINST ROCK SPIRIT","POWER AGAINST EVIL CONSUMPTION","I WILL NEVER SURRENDER TO THE ENEMY","DESTROYING DISEASE GERMS","FIRE PRAYERS","WAR AGAINST THE RAGE OF CORONAVIRUS PANDEMIC","I KNOW THAT MY REDEEMER LIVETH","BARRENNESS MUST DIE","O ROCK OF AGES, ARISE AND FIGHT MY BATTLE","THOU SHALL BRUISE THE HEAD OF THE SERPENT","OH LORD, SILENCE MY MOCKERS","I SHALL LAUGH LAST OVER ALL MY PROBLEMS","PRAYER TO CRUSH THE SEAT AND DECISION OF EVIL ELDERS","IN THE MIDST OF THE MULTITUDE, OH GOD SINGLE ME OUT FOR TESTIMONY",
                "THE LORD WILL PERFECT THAT WHICH CONCERNS ME","HOLY GHOST IMMUNIZATION","AVENGE ME OF MY ADVERSARIES",
                "CANCER MUST DIE","CONFESSION AND PRAYER FOR SUPERNATURAL CONCEPTION","CONFESSION THAT BRING PROSPERITY","CRUSHING THE GRIP OF EVIL ANCESTRAL POWERS","CUTTING EVIL LINKAGE","DAILY BREAKFAST","DEALING WITH LUCIFERIAN NIGHT PLANTERS","DEALING WITH MARINE POWERS","DISSOLVING UNPROFITABLE GROWTH","ERASING EVIL MARKS","EXAMINATION SUCCESS_PRAY YOUR WAY TO BREAKTHROUGHS",
                "FEAR MUST DIE","HEAL ME SAVE ME","HEALING THE WOUNDED SPIRIT","I DECREE BREAKTHROUGHS","I SHALL NOT COOPERATE WITH DEMOTION","IT IS MY TIME TO LAUGH","KILL THEIR PROPHETS","KILLING THE GOLIATH AFFECTING YOUR DESTINY",
                "KILLING THE SEED OF DARKNESS","KNOW THE SECRET","MY DESTINY SHALL NOT DIE","O GOD ARISE AND BREAK MY CHAINS","O GOD OF JABEZ","O LORD ADVANCE MY CAUSE","O LORD LET MY LIFE EXPERIENCE YOUR MIRACLE",
                "O LORD MAKE ME YOUR BATTLE AXE","O LORD, LET MY PORTIONS BE RELEASED","O LORD, LET YOUR MANTLE OF FIRE FALL ON ME","PERSONAL DELIVERANCE PRAYERS FOR MINISTERS",
                "PERSONAL PROPHECIES TO MOVE YOU FORWARD","POSSESSING THE HIDDEN RICHES IN THE SECRET PLACES",
                "POWER AGAINST DREAM INVADERS","POWER AGAINST EVIL ANCESTRAL TRANSMISSION",
                "POWER AGAINST EVIL PATTERNS","POWER AGAINST INTROJECTS","POWER AGAINST SEXUAL PERVERSION","POWER AGAINST THE SPIRIT OF THE SNAIL","POWER AGAINST WATER SPIRITS","POWER FOR A NEW BEGINNING",
                "POWER FOR THE NEXT LEVEL","POWER FOR UNCHALLENGEABLE PROGRESS","POWER FOR UNIMAGINABLE DELIVERANCE","POWER TO PROSPER","POWER TO RECEIVE FRESH FIRE","PRAYER AGAINST UNTIMELY DEATH","PRAYERS TO DESTROY EFFECTS OF EVIL FOUNDATIONS","PRAYERS TO DISGRACE THE ENEMIES","PRAYERS TO HEAL ASTHMA","PRAYERS TO REVOKE EVIL DEDICATIONS","PULLING DOWN THE STRONGHOLD OF SATANIC DELAY","PURSUE, OVERTAKE AND RECOVERY","RECLAIM YOUR TRAPPED BLESSINGS","RELEASE FROM COLLECTIVE CAPTIVITY","RELEASE FROM DESTRUCTIVE COVENANTS","RETRENCH SATANIC PRIESTS","REVERSING HIDDEN CURSES","SANCTIFYING NEW HOUSE OR LANDS","SILENCING SATANIC PROPHETS","SILENCING THE EVIL CRY OF ANCESTRAL IDOLS","THE SPEAKING BLOOD","THOU POWER OF ANCESTRAL IDOLS DIE","VICTORY IN THE HUMAN COURT","WALL OF JERICHO, BE DESTROYED","WASTE THE WASTERS","COMMAND THE YEAR","CREATING GOOD WORKING RELATIONSHIP","CRUSHING THE POWER OF THE FOWLER","DEALING WITH THE SPIRIT OF DEATH AND HELL","LORD PURGE MY FOUNDATIONS","POWER AGAINST SEXUAL PERVERSION","O LORD SHOW ME THE SECRET OF MY PROBLEM","LET MY DAY BEGIN TO SPEAK","WICKEDNESS MUST EXPIRE","I FIGHT LIKE THE PSALMIST","BEFORE ME, GREAT MOUNTAINS SHALL BECOME A PLAIN",
                "POWER AGAINST DESTINY ROBBERS","PRAYERS AGAINST STRANGE WOMEN AND MEN IN MARRIAGE"};
        this.laptopCollection = new LinkedHashMap();
        for (String laptop : this.groupList) {
            if (laptop.equals("POWER MUST CHANGE HANDS PRAYERS\n\n(2006 - 2020)")) {
                loadChild(x1);
            } else if (laptop.equals("PRAYER POINTS FOR MARRIAGE\n\n(----------------**********----------------)")) {
                loadChild(x2);
            } else if (laptop.equals("CONFESSION FOR PREGNANT WOMEN\n\n(----------------**********----------------)")) {
                loadChild(x3);
            } else if (laptop.equals("PRAYER AGAINST WITCHCRAFT'S\n\n(----------------**********----------------)")) {
                loadChild(x4);
            } else if (laptop.equals("PRAYERS FOR DIVINE FAVOUR\n\n(----------------**********----------------)")) {
                loadChild(x5);
            } else if (laptop.equals("BODAGE-BREAKING PRAYERS\n\n(----------------**********----------------)")) {
                loadChild(x6);
            } else if (laptop.equals("DANGEROUS PRAYERS\n\n(----------------**********----------------)")) {
                loadChild(x7);
            }else if (laptop.equals("DELIVERANCE PRAYER\n\n(----------------**********----------------)")){
                loadChild(x8);
            } else if (laptop.equals("OTHERS\n\n(----------------**********----------------)")) {
                loadChild(x9);
            }
            this.laptopCollection.put(laptop, this.childList);
        }
    }

    @SuppressLint("WrongConstant")
    public void voiceIDs(View vb) {
        vb.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        Toast.makeText(getActivity(), "Speak the title of the Prayer you want to search...", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "Speak the title of the Prayer you want to search...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        startActivityForResult(intent, 1234);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            getActivity();
            if (resultCode == -1) {
                this.autoComplete.setText(data.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
            }
        }
    }

    private void loadChild(String[] laptopModels) {
        this.childList = new ArrayList();
        for (String model : laptopModels) {
            this.childList.add(model);
        }
    }

    private void setGroupIndicatorToRight() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        this.expListView.setIndicatorBounds(width - getDipsFromPixel(35.0f), width - getDipsFromPixel(5.0f));
    }

    public int getDipsFromPixel(float pixels) {
        return (int) ((pixels * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.srch = getView().findViewById(R.id.search);
        this.autoComplete.selectAll();
        String message = this.srch.getText().toString();
        Intent k = new Intent(getActivity(), PrayerOutput.class);
        k.putExtra("english", message);
        startActivity(k);
        getActivity().overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    public void onDestroy() {
        super.onDestroy();
        System.gc();
        getActivity().finish();
    }
}