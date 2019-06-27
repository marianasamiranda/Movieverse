import { en } from './translations/en'
import { pt } from './translations/pt'
import { de } from './translations/de'
import { el } from './translations/el'
import { es } from './translations/es'
import { fr } from './translations/fr'
import { hi } from './translations/hi'
import { id } from './translations/id'
import { it } from './translations/it'
import { ja } from './translations/ja'
import { ko } from './translations/ko'
import { nl } from './translations/nl'
import { ru } from './translations/ru'
import { zh_cn } from './translations/zh-cn'
import { ar } from './translations/ar'

export const backend = 'http://localhost:8080'

export const avatars = window.location.origin + "/avatars/"
export const badges_location = window.location.origin + "/badges/"

export const genres = [
  'Action',
  'Adventure',
  'Animation',
  'Comedy',
  'Crime',
  'Documentary',
  'Drama',
  'Family',
  'Fantasy',
  'History',
  'Horror',
  'Music',
  'Mystery',
  'Romance',
  'Science Fiction',
  'TV Movie',
  'Thriller',
  'War',
  'Western',
  'None',
]
export const theaters = {
  "Cinemas NOS Braga Parque": { id: 1, coords: [41.557568, -8.406558] },
  "Cineplace Nova Arcada": { id: 2, coords: [41.578312, -8.430086] },
  "Cinemax Braga": { id: 3, coords: [41.551788, -8.42221] },
  "Cinemax Barcelos": { id: 4, coords: [41.535751, -8.615790] },
  "Fórum Vizela Cinema": { id: 5, coords: [41.378847, -8.311013] },
  "Castello Lopes Espaço Guimarães": { id: 6, coords: [41.509567, -8.238824] },
  "Cinemas NOS Gaia Shopping": { id: 7, coords: [41.117928, -8.622507] },
  "Cinemas NOS Maia Shopping": { id: 8, coords: [41.219235, -8.561404] },
  "Cinemas NOS NorteShopping": { id: 9, coords: [41.181770, -8.654400] },
  "Cinemas NOS Parque Nascente": { id: 10, coords: [41.174301, -8.566650] },
  "Cineplace Mira Maia": { id: 11, coords: [41.255466, -8.653536] },
  "Cinemax Penafiel": { id: 12, coords: [41.207481, -8.278110] },
  "Cinemas NOS Ferrara Plaza": { id: 13, coords: [41.284595, -8.361249] },
  "Cinemas NOS Alameda Shop e Spot": { id: 14, coords: [41.162933, -8.584280] },
  "Cinemas NOS Nosso Shopping": { id: 15, coords: [41.297188, -7.735276] },
  "Cinemas NOS Glicínias": { id: 16, coords: [40.628029, -8.645060] },
  "Cinemas NOS Fórum Viseu": { id: 17, coords: [40.661920, -7.914334] },
  "UCI Arrábida 20": { id: 18, coords: [41.140584, -8.636641] },
  "Cinemas NOS Palácio do Gelo": { id: 19, coords: [40.643717, -7.910701] },
  "Cinemas NOS Alma Shopping Coimbra": { id: 20, coords: [40.204616, -8.407600] },
  "Cinemas NOS Fórum Coimbra": { id: 21, coords: [40.211263, -8.442833] },
  "Cinemas NOS Colombo": { id: 22, coords: [38.755131, -9.187058] },
  "Cinemas NOS Odivelas Strada": { id: 23, coords: [38.782192, -9.191667] },
  "Cinemas NOS Vasco da Gama": { id: 24, coords: [38.768055, -9.097313] },
  "Cinemas NOS Fórum Montijo": { id: 25, coords: [38.695366, -8.942201] },
  "Cinemas NOS Alvaláxia": { id: 26, coords: [38.760989, -9.159871] },
  "Cinemas NOS Almada Fórum": { id: 27, coords: [38.660752, -9.175163] },
  "Cinemas NOS Fórum Algarve": { id: 28, coords: [37.029122, -7.946070] },
  "Cinema City - Leiria": { id: 29, coords: [39.746406, -8.821047] },
  "Cinema City - Campo Pequeno": { id: 30, coords: [38.742346, -9.144920] },
  "UCI El Corte Inglés": { id: 31, coords: [38.732684, -9.153275] },
  "Cinema City - Alegro Alfragide": { id: 32, coords: [38.727603, -9.219050] },
  "Cinebox Cinemas": { id: 33, coords: [39.807080, -7.521982] },
  "Cinemas NOS Algarve Mar Shopping": { id: 34, coords: [37.097494, -7.996047] },
  "Cinema City - Alegro Setúbal": { id: 35, coords: [38.537447, -8.879276] },
  "Cinemas NOS Evora Plaza": { id: 36, coords: [38.549552, -7.905243] },
  "Cineplace AlgarveShopping": { id: 37, coords: [37.127757, -8.279695] }
}

export const badges = [
  {
    name: "1 year old",
    description: "Celebrate 1 year since the account creation",
    image: "1-year-old.png"
  },
  {
    name: "2 years old",
    description: "Celebrate 2 years since the account creation",
    image: "2-years-old.png"
  },
  {
    name: "3 years old",
    description: "Celebrate 3 years since the account creation",
    image: "3-years-old.png"
  },
  {
    name: "4 years old",
    description: "Celebrate 4 years since the account creation",
    image: "4-years-old.png"
  },
  {
    name: "5 years old",
    description: "Celebrate 5 years since the account creation",
    image: "5-years-old.png"
  },
  {
    name: "10 movie hours",
    description: "Watch in total 10 movie hours",
    image: "10-movie-hours.png"
  },
  {
    name: "50 movie hours",
    description: "Watch in total 50 movie hours",
    image: "50-movie-hours.png"
  },
  {
    name: "100 movie hours",
    description: "Watch in total 100 movie hours",
    image: "100-movie-hours.png"
  },
  {
    name: "500 movie hours",
    description: "Watch in total 500 movie hours",
    image: "500-movie-hours.png"
  },
  {
    name: "1000 movie hours",
    description: "Watch in total 1000 movie hours",
    image: "1000-movie-hours.png"
  },
  {
    name: "1 like single comment",
    description: "Achieve 1 like in a single comment",
    image: "1-like-single-comment.png"
  },
  {
    name: "10 likes single comment",
    description: "Achieve 10 likes in a single comment",
    image: "10-likes-single-comment.png"
  },
  {
    name: "50 likes single comment",
    description: "Achieve 50 likes in a single comment",
    image: "50-likes-single-comment.png"
  },
  {
    name: "100 likes single comment",
    description: "Achieve 100 likes in a single comment",
    image: "100-likes-single-comment.png"
  },
  {
    name: "500 likes single comment",
    description: "Achieve 500 likes in a single comment",
    image: "500-likes-single-comment.png"
  },
  {
    name: "1000 likes single comment",
    description: "Achieve 1000 likes in a single comment",
    image: "1000-likes-single-comment.png"
  },
  {
    name: "1 friend",
    description: "Add 1 friend",
    image: "1-friend.png"
  },
  {
    name: "5 friends",
    description: "Add 5 friends",
    image: "5-friends.png"
  },
  {
    name: "15 friends",
    description: "Add 15 friends",
    image: "15-friends.png"
  },
  {
    name: "50 friends",
    description: "Add 50 friends",
    image: "50-friends.png"
  },
  {
    name: "100 friends",
    description: "Add 100 friends",
    image: "100-friends.png"
  },
  {
    name: "First favourite movie",
    description: "Add a movie to the favourites",
    image: "first-favourite-movie.png"
  },
  {
    name: "First rating",
    description: "Add a rating to a movie",
    image: "first-rating.png"
  },
  {
    name: "First comment",
    description: "Add a comment to a movie",
    image: "first-comment.png"
  }
]

//translations

export const labels = {
  en: en,
  en: en,
  pt: pt,
  de: de,
  el: el,
  es: es,
  fr: fr,
  hi: hi,
  id: id,
  it: it,
  ja: ja,
  ko: ko,
  nl: nl,
  ru: ru,
  zh_cn: zh_cn,
  ar: ar
}

export const screen_size_content_limit = [
  {'wsize': 0, 'limit': 190, 'maxh': 150, 'minh': 70},
  {'wsize': 400, 'limit': 100, 'maxh': 200, 'minh': 90},
  {'wsize': 500, 'limit': 190, 'maxh': 200, 'minh': 120},
  {'wsize': 550, 'limit': 240, 'maxh': 200, 'minh': 120},
  {'wsize': 767, 'limit': 320, 'maxh': 200, 'minh': 120},
  {'wsize': 992, 'limit': 180, 'maxh': 200, 'minh':120},
  {'wsize': 1198, 'limit': 240, 'maxh': 200, 'minh':120}
]

////////////////

export const selectStyles = {
  control: (base, state) => ({
    ...base,
    background: "#e9e9e9",
    minHeight: '36px',
    fontFamily: 'Roboto',
    marginBottom: '10px',
    hover:  {
      cursor: 'pointer'
    }
    
  }),
  menu: base => ({
    ...base,
    borderRadius: 0,
    marginTop: 0,
  }),
  menuList: base => ({
    ...base,
    fontFamily: 'Roboto',
    padding: 0
  }),
  clearIndicator: base => ({
    ...base,
    padding: 4
  }),
  valueContainer: base => ({
    ...base,
    padding: '0px 10px',
  }),
  input: base => ({
    ...base,
    margin: 0,
    padding: 0
  }),
  multiValue: (styles, { data }) => {
    return {
      ...styles,
      backgroundColor: '#d0d0d0',
      marginRight: '5px'
    };
  },
  multiValueLabel: (styles, { data }) => ({
    ...styles,
    color: data.color,
  }),
  multiValueRemove: (styles, { data }) => ({
    ...styles,
    color: data.color,
    ':hover': {
      backgroundColor: data.color,
      color: 'red',
    },
  }),
};