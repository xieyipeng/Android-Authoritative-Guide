package com.example.xieyipeng.criminallintent.tools;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xieyipeng on 2018/9/18.
 */

public class CrimeLab {
    private static CrimeLab crimeLab;
    private List<Crime> crimes;

    public static CrimeLab get(Context context) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    public void add(Crime c){
        crimes.add(c);
    }

    private CrimeLab(Context context) {
        crimes = new ArrayList<>();
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime :
                crimes) {
            if (crime.getUuid().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
