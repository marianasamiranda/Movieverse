import { getToken } from "./cookies";

export const backend = 'http://localhost:8080'

export const avatars = window.location.origin + "/avatars/"

export const genres = {
  Action: { value: 'Action', label: '🔥 Action' },
  Adventure: { value: 'Adventure', label: '🗺️ Adventure' },
  Animation: { value: 'Animation', label: '🐭 Animation' },
  Comedy: { value: 'Comedy', label: '😂 Comedy' },
  Crime: { value: 'Crime', label: '👮 Crime' },
  Documentary: { value: 'Documentary', label: '💡 Documentary' },
  Drama: { value: 'Drama', label: '🎭 Drama' },
  Family: { value: 'Family', label: '👪 Family' },
  Fantasy: { value: 'Fantasy', label: '🗡️ Fantasy' },
  History: { value: 'History', label: '📜 History' },
  Horror: { value: 'Horror', label: '👻 Horror' },
  Music: { value: 'Music', label: '🎵 Music' },
  Mystery: { value: 'Mystery', label: '🕵️‍ Mystery' },
  Romance: { value: 'Romance', label: '❤️ Romance' },
  'Science Fiction': { value: 'Science Fiction', label: '🤖 Science Fiction' },
  'TV Movie': { value: 'TV Movie', label: '📺 TV Movie' },
  Thriller: { value: 'Thriller', label: '🔪 Thriller' },
  War: { value: 'War', label: '⚔️ War' },
  Western: { value: 'Western', label: '🤠 Western' },
  None: {value: 'None', label:'None'}
}

export const theaters = {
  "Cinemas NOS Braga Parque": 1,
  "Cineplace Nova Arcada": 2,
  "Cinemax Braga": 3,
  "Cinemax Barcelos": 4,
  "Fórum Vizela Cinema": 5,
  "Castello Lopes Espaço Guimarães": 6,
  "Cinemas NOS Gaia Shopping": 7,
  "Cinemas NOS Maia Shopping": 8,
  "Cinemas NOS NorteShopping": 9,
  "Cinemas NOS Parque Nascente": 10,
  "Cineplace Mira Maia": 11,
  "Cinemax Penafiel": 12,
  "Cinemas NOS Ferrara Plaza": 13,
  "Cinemas NOS Alameda Shop e Spot": 14,
  "Cinemas NOS Nosso Shopping": 15,
  "Cinemas NOS Glicínias": 16,
  "Cinemas NOS Fórum Viseu": 17,
  "UCI Arrábida 20": 18,
  "Cinemas NOS Palácio do Gelo": 19,
  "Cinemas NOS Alma Shopping Coimbra": 20,
  "Cinemas NOS Fórum Coimbra": 21,
  "Cinemas NOS Colombo": 22,
  "Cinemas NOS Odivelas Strada": 23,
  "Cinemas NOS Vasco da Gama": 24,
  "Cinemas NOS Fórum Montijo": 25,
  "Cinemas NOS Alvaláxia": 26,
  "Cinemas NOS Almada Fórum": 27,
  "Cinemas NOS Fórum Algarve": 28,
  "Cinema City - Leiria": 29,
  "Cinema City - Campo Pequeno": 30,
  "UCI El Corte Inglés": 31,
  "Cinema City - Alegro Alfragide": 32,
  "Cinebox Cinemas": 33,
  "Cinemas NOS Algarve Mar Shopping": 34,
  "Cinema City - Alegro Setúbal": 35,
  "Cinemas NOS Evora Plaza": 36,
  "Cineplace AlgarveShopping": 3
}


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