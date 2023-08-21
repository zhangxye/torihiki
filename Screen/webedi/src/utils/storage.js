// 获取数据
export const getItem = key => {
    const data = window.localStorage.getItem(key)

    // 用try catch来进行 json的转换
    try {
        return JSON.parse(data)
    } catch (err) {
        return data
    }
}

// 存储数据
export const setItem = (key, value) => {
    // 将对象类型的数据 转换为json字符串格式
    if (typeof value === 'object') {
        value = JSON.stringify(value)
    }
    window.localStorage.setItem(key, value)
}

// 清除数据
export const removeItem = key => {
    window.localStorage.removeItem(key)
}

export const getSessionItem = key => {
    const data = window.sessionStorage.getItem(key)

    // 用try catch来进行 json的转换
    try {
        return JSON.parse(data)
    } catch (err) {
        return data
    }
}

// 存储数据
export const setSessionItem = (key, value) => {
    // 将对象类型的数据 转换为json字符串格式
    if (typeof value === 'object') {
        value = JSON.stringify(value)
    }
    window.sessionStorage.setItem(key, value)
}

// 清除数据
export const removeSessionItem = key => {
    window.sessionStorage.removeItem(key)
}
