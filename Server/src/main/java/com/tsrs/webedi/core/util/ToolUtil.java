/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tsrs.webedi.core.util;

import com.tsrs.webedi.config.properties.WebEDIProperties;
import com.tsrs.webedi.core.support.StrKit;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * 高频方法集合类
 */
public class ToolUtil {

	/**
	 * 判断一个对象是否是时间类型
	 * 
	 * @author tsrs
	 * @Date 2017/4/18 12:55
	 */
	public static String dateType(Object o) {
		if (o instanceof Date) {
			return DateUtil.getDay((Date) o);
		} else {
			return o.toString();
		}
	}

	/**
	 * 获取异常的具体信息
	 *
	 * @author Tsrs-WebEDI
	 * @Date 2017/3/30 9:21
	 * @version 2.0
	 */
	public static String getExceptionMsg(Exception e) {
		StringWriter sw = new StringWriter();
		try {
			e.printStackTrace(new PrintWriter(sw));
		} finally {
			try {
				sw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return sw.getBuffer().toString().replaceAll("\\$", "T");
	}

	/**
	 * @Description 主键id
	 * @author Tsrs-WebEDI
	 */
	public static String getUid() {
		return getRandomNum();
	}

	/**
	 * @Description 随机数字
	 * @author Tsrs-WebEDI
	 */
	public static String getRandomNum() {
		return Calendar.getInstance().getTimeInMillis() + generateCellPhoneValNum();
	}

	/**
	 * @Description 获取電話号码
	 * @author Tsrs-WebEDI
	 */
	public static String generateCellPhoneValNum() {
		String[] beforeShuffle = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			buffer.append(list.get(i));
		}
		String afterShuffle = buffer.toString();
		String result = afterShuffle.substring(3, 9);
		return result;
	}

	/**
	 * 比较两个对象是否相等。<br>
	 * 相同的条件有两个，满足其一即可：<br>
	 * 1. obj1 == null && obj2 == null; 2. obj1.equals(obj2)
	 *
	 * @param obj1
	 *            对象1
	 * @param obj2
	 *            对象2
	 * @return 是否相等
	 */
	public static boolean equals(Object obj1, Object obj2) {
		return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
	}

	/**
	 * 计算对象长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度
	 *
	 * @param obj
	 *            被计算长度的对象
	 * @return 长度
	 */
	public static int length(Object obj) {
		if (obj == null) {
			return 0;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length();
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).size();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).size();
		}

		int count;
		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			count = 0;
			while (iter.hasNext()) {
				count++;
				iter.next();
			}
			return count;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) obj;
			count = 0;
			while (enumeration.hasMoreElements()) {
				count++;
				enumeration.nextElement();
			}
			return count;
		}
		if (obj.getClass().isArray() == true) {
			return Array.getLength(obj);
		}
		return -1;
	}

	/**
	 * 对象中是否包含元素
	 *
	 * @param obj
	 *            对象
	 * @param element
	 *            元素
	 * @return 是否包含
	 */
	public static boolean contains(Object obj, Object element) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			if (element == null) {
				return false;
			}
			return ((String) obj).contains(element.toString());
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).contains(element);
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).values().contains(element);
		}

		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			while (iter.hasNext()) {
				Object o = iter.next();
				if (equals(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) obj;
			while (enumeration.hasMoreElements()) {
				Object o = enumeration.nextElement();
				if (equals(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj.getClass().isArray() == true) {
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				Object o = Array.get(obj, i);
				if (equals(o, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 对象是否不为空(新增)
	 *
	 * @param obj
	 *            String,List,Map,Object[],int[],long[]
	 * @return
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * 对象是否为空
	 *
	 * @param obj
	 *            String,List,Map,Object[],int[],long[]
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			if (o.toString().trim().equals("")) {
				return true;
			}
		} else if (o instanceof List) {
			if (((List) o).size() == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).size() == 0) {
				return true;
			}
		} else if (o instanceof Set) {
			if (((Set) o).size() == 0) {
				return true;
			}
		} else if (o instanceof Object[]) {
			if (((Object[]) o).length == 0) {
				return true;
			}
		} else if (o instanceof int[]) {
			if (((int[]) o).length == 0) {
				return true;
			}
		} else if (o instanceof long[]) {
			if (((long[]) o).length == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 对象组中是否存在 Empty Object
	 *
	 * @param os
	 *            对象组
	 * @return
	 */
	public static boolean isOneEmpty(Object... os) {
		for (Object o : os) {
			if (isEmpty(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 对象组中是否全是 Empty Object
	 *
	 * @param os
	 * @return
	 */
	public static boolean isAllEmpty(Object... os) {
		for (Object o : os) {
			if (!isEmpty(o)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否为数字
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isNum(Object obj) {
		try {
			Integer.parseInt(obj.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 如果为空, 则调用默认值
	 *
	 * @param str
	 * @return
	 */
	public static Object getValue(Object str, Object defaultValue) {
		if (isEmpty(str)) {
			return defaultValue;
		}
		return str;
	}

	/**
	 * 格式化文本
	 *
	 * @param template
	 *            文本模板，被替换的部分用 {} 表示
	 * @param values
	 *            参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... values) {
		return StrKit.format(template, values);
	}

	/**
	 * 格式化文本
	 *
	 * @param template
	 *            文本模板，被替换的部分用 {key} 表示
	 * @param map
	 *            参数值对
	 * @return 格式化后的文本
	 */
	public static String format(String template, Map<?, ?> map) {
		return StrKit.format(template, map);
	}

	/**
	 * 强转->string,并去掉多余空格
	 *
	 * @param str
	 * @return
	 */
	public static String toStr(Object str) {
		return toStr(str, "");
	}

	/**
	 * 强转->string,并去掉多余空格
	 *
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String toStr(Object str, String defaultValue) {
		if (null == str) {
			return defaultValue;
		}
		return str.toString().trim();
	}

	/**
	 * 强转->int
	 *
	 * @param obj
	 * @return
	 */
	// public static int toInt(Object value) {
	// return toInt(value, -1);
	// }

	/**
	 * 强转->int
	 *
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	// public static int toInt(Object value, int defaultValue) {
	// return Convert.toInt(value, defaultValue);
	// }

	/**
	 * 强转->long
	 *
	 * @param obj
	 * @return
	 */
	// public static long toLong(Object value) {
	// return toLong(value, -1);
	// }

	/**
	 * 强转->long
	 *
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	// public static long toLong(Object value, long defaultValue) {
	// return Convert.toLong(value, defaultValue);
	// }
	//
	// public static String encodeUrl(String url) {
	// return URLKit.encode(url, CharsetKit.UTF_8);
	// }
	//
	// public static String decodeUrl(String url) {
	// return URLKit.decode(url, CharsetKit.UTF_8);
	// }

	/**
	 * map的key转为小写
	 *
	 * @param map
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> caseInsensitiveMap(Map<String, Object> map) {
		Map<String, Object> tempMap = new HashMap<>();
		for (String key : map.keySet()) {
			tempMap.put(key.toLowerCase(), map.get(key));
		}
		return tempMap;
	}

	/**
	 * 获取map中第一个数据值
	 *
	 * @param <K>
	 *            Key的类型
	 * @param <V>
	 *            Value的类型
	 * @param map
	 *            数据源
	 * @return 返回的值
	 */
	public static <K, V> V getFirstOrNull(Map<K, V> map) {
		V obj = null;
		for (Entry<K, V> entry : map.entrySet()) {
			obj = entry.getValue();
			if (obj != null) {
				break;
			}
		}
		return obj;
	}

	/**
	 * 创建StringBuilder对象
	 *
	 * @return StringBuilder对象
	 */
	public static StringBuilder builder(String... strs) {
		final StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str);
		}
		return sb;
	}

	/**
	 * 创建StringBuilder对象
	 *
	 * @return StringBuilder对象
	 */
	public static void builder(StringBuilder sb, String... strs) {
		for (String str : strs) {
			sb.append(str);
		}
	}

	/**
	 * 去掉指定后缀
	 *
	 * @param str
	 *            字符串
	 * @param suffix
	 *            后缀
	 * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
	 */
	public static String removeSuffix(String str, String suffix) {
		if (isEmpty(str) || isEmpty(suffix)) {
			return str;
		}

		if (str.endsWith(suffix)) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}

	/**
	 * 当前时间
	 *
	 * @author tsrs
	 * @Date 2017/5/7 21:56
	 */
	public static String currentTime() {
		return DateUtil.getTime();
	}

	/**
	 * 首字母大写
	 *
	 * @author tsrs
	 * @Date 2017/5/7 22:01
	 */
	public static String firstLetterToUpper(String val) {
		return StrKit.firstCharToUpperCase(val);
	}

	/**
	 * 首字母小写
	 *
	 * @author tsrs
	 * @Date 2017/5/7 22:02
	 */
	public static String firstLetterToLower(String val) {
		return StrKit.firstCharToLowerCase(val);
	}

	/**
	 * 获取验证码开关
	 *
	 * @author tsrs
	 * @Date 2017/5/23 22:34
	 */
	public static Boolean getKaptchaOnOff() {
		return SpringContextHolder.getBean(WebEDIProperties.class).getKaptchaOpen();
	}

	/**
	 * 判断是否是windows操作系统
	 *
	 * @author tsrs
	 * @Date 2017/5/24 22:34
	 */
	public static Boolean isWinOs() {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取临时目录
	 *
	 * @author tsrs
	 * @Date 2017/5/24 22:35
	 */
	public static String getTempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static Object copy(Object object) throws Exception {
		Class<?> classType = object.getClass();

		/*
		 * 生成新的对象的讨论 // 获得Constructor对象,此处获取第一个无参数的构造方法的 Constructor cons =
		 * classType.getConstructor(new Class[] {});//不带参数，所以传入一个为空的数组 //
		 * 通过构造方法来生成一个对象 Object obj = cons.newInstance(new Object[] {});
		 * 
		 * // 以上两行代码等价于： Object obj11 = classType.newInstance(); //
		 * 这行代码无法处理构造函数有参数的情况
		 * 
		 * //用第二个带参数的构造方法生成对象 Constructor cons2 = classType.getConstructor(new
		 * Class[] {String.class, int.class}); Object obj2 =
		 * cons2.newInstance(new Object[] {"ZhangSan",20});
		 * 
		 */

		Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});

		// 获得对象的所有成员变量
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			// 获取成员变量的名字
			String name = field.getName(); // 获取成员变量的名字，此处为id，name,age
			// System.out.println(name);

			// 获取get和set方法的名字
			String firstLetter = name.substring(0, 1).toUpperCase(); // 将属性的首字母转换为大写
			String getMethodName = "get" + firstLetter + name.substring(1);
			String setMethodName = "set" + firstLetter + name.substring(1);
			// System.out.println(getMethodName + "," + setMethodName);

			// 获取方法对象
			Method getMethod = classType.getMethod(getMethodName, new Class[] {});
			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });// 注意set方法需要传入参数类型

			// 调用get方法获取旧的对象的值
			Object value = getMethod.invoke(object, new Object[] {});
			// 调用set方法将这个值复制到新的对象中去
			setMethod.invoke(objectCopy, new Object[] { value });

		}

		return objectCopy;

	}

	public static Object copy(Map<String, Object> srcObject, Object object) throws Exception {
		Class<?> classType = object.getClass();

		Object objectCopy = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});

		// 获得对象的所有成员变量
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			// 获取成员变量的名字
			String name = field.getName(); // 获取成员变量的名字，此处为id，name,age
			// System.out.println(name);

			// 获取get和set方法的名字
			String firstLetter = name.substring(0, 1).toUpperCase(); // 将属性的首字母转换为大写

			String setMethodName = "set" + firstLetter + name.substring(1);
			// System.out.println(getMethodName + "," + setMethodName);

			// 获取方法对象

			Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() });// 注意set方法需要传入参数类型

			// 调用get方法获取旧的对象的值
			Object value = srcObject.get(name);
			try {
			if (null != value) {
				// 调用set方法将这个值复制到新的对象中去
				if (field.getGenericType().toString().equals("class java.util.Date")) {
					Date dt = new Date();
                        setMethod.invoke(objectCopy, Convert.parse(dt.getClass(), value));
				} else if (field.getGenericType().toString().equals("class java.math.BigDecimal")) {
					BigDecimal bd = BigDecimal.ZERO;
                        setMethod.invoke(objectCopy, Convert.parse(bd.getClass(), value));
				}else if (field.getGenericType().toString().equals("class java.lang.Integer")) {
					Integer bd = Integer.MIN_VALUE;
                        setMethod.invoke(objectCopy, Convert.parse(bd.getClass(), value));
				} else if (field.getGenericType().toString().equals("class java.lang.boolean")) {
                        setMethod.invoke(objectCopy, Convert.toBool(value));
				} else {
                        setMethod.invoke(objectCopy, value);
				}
			}
			} catch(Exception e) {
				throw e;
			}
		}

		return objectCopy;

	}

}