import PinCard from './PinCard'

const MasonryLayout = ({ pins }) => {
  return (
    <div className="columns-1 sm:columns-2 md:columns-3 lg:columns-4 gap-4 space-y-4">
      {pins.map(pin => (
        <div key={pin.id} className="break-inside-avoid">
          <PinCard imageUrl={pin.imageUrl} />
        </div>
      ))}
    </div>
  )
}

export default MasonryLayout
