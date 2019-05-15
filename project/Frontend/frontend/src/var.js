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