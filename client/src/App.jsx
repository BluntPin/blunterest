import { useEffect, useState } from 'react'
import { getAllPins } from './services/pinService'
import MasonryLayout from './components/MasonryLayout'

function App() {
  const [pins, setPins] = useState([])

  useEffect(() => {
    getAllPins()
      .then(data => setPins(data))
      .catch(err => console.error('Error fetching pins:', err))
  }, [])

  return (
    <main className="p-4 bg-gray-100 min-h-screen">
      <MasonryLayout pins={pins} />
    </main>
  )
}

export default App