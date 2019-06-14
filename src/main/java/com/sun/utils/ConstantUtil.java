package com.sun.utils;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ConstantUtil {

    public final static String SECURITY_CODE = "293492bdc08181fa44f0ed4852c3e06c";

    public final static Integer ADMIN_ROLE = 0;
    public final static Integer ORDINARY_ROLE = 1;

    public final static Boolean IS_BANDED = true;
    public final static Boolean NO_BANDED = false;

    public final static Boolean IS_SUCCESS = true;
    public final static Boolean NO_SUCCESS = false;

    public final static Integer PAGE_SIZE = 5;

    public final static Integer QUESTION_TRUE_OR_FALSE = 0;
    public final static Integer QUESTION_SINGLE_SELECTION = 1;
    public final static Integer QUESTION_MULTIPLE_SELECTION = 2;
    public final static Integer QUESTION_SUBJECTIVE = 3;

}
