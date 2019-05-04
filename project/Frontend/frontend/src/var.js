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

export const theaters = [
  "Cinemas NOS Braga Parque",
  "Cineplace Nova Arcada",
  "Cinemax Braga",
  "Cinemax Barcelos",
  "Fórum Vizela Cinema",
  "Castello Lopes Espaço Guimarães",
  "Cinemas NOS Gaia Shopping",
  "Cinemas NOS Maia Shopping",
  "Cinemas NOS NorteShopping",
  "Cinemas NOS Parque Nascente",
  "Cineplace Mira Maia",
  "Cinemax Penafiel",
  "Cinemas NOS Ferrara Plaza",
  "Cinemas NOS Alameda Shop e Spot",
  "Cinemas NOS Nosso Shopping",
  "Cinemas NOS Glicínias",
  "Cinemas NOS Fórum Viseu",
  "UCI Arrábida 20",
  "Cinemas NOS Palácio do Gelo",
  "Cinemas NOS Alma Shopping Coimbra",
  "Cinemas NOS Fórum Coimbra",
  "Cinemas NOS Colombo",
  "Cinemas NOS Odivelas Strada",
  "Cinemas NOS Vasco da Gama",
  "Cinemas NOS Fórum Montijo",
  "Cinemas NOS Alvaláxia",
  "Cinemas NOS Almada Fórum",
  "Cinemas NOS Fórum Algarve",
  "Cinema City - Leiria",
  "Cinema City - Campo Pequeno",
  "UCI El Corte Inglés",
  "Cinema City - Alegro Alfragide",
  "Cinebox Cinemas",
  "Cinemas NOS Algarve Mar Shopping",
  "Cinema City - Alegro Setúbal",
  "Cinemas NOS Evora Plaza",
  "Cineplace AlgarveShopping"
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