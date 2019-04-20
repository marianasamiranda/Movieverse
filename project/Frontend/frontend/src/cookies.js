import Cookies from 'universal-cookie'

const cookies = new Cookies()

export function setToken(value) {
    cookies.set('token', value, {path: '/', expires: new Date(Date.now()+259200000000)})
}

export function getToken() {
    return cookies.get('token')
}

export function clearToken() {
    const token = cookies.get('token')
    cookies.remove('token')
    return token
}