export type CreateTripInput = {
  name: string
  startDate: string
  endDate: string
  timeZone: string
  creatorId: string
}

export type Trip = CreateTripInput & {
  id: string
  createdAt: string
}

export async function createTrip(input: CreateTripInput): Promise<Trip> {
  const response = await fetch('/api/trips', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(input),
  })

  const data: unknown = await response.json()

  if (!response.ok) {
    if (
      typeof data === 'object' &&
      data !== null &&
      'message' in data &&
      typeof data.message === 'string'
    ) {
      throw new Error(data.message)
    }

    throw new Error(`API returned status ${response.status}`)
  }

  if (!isTrip(data)) {
    throw new Error('API returned an invalid trip response')
  }

  return data
}

function isTrip(value: unknown): value is Trip {
  return (
    typeof value === 'object' &&
    value !== null &&
    'id' in value &&
    typeof value.id === 'string' &&
    'name' in value &&
    typeof value.name === 'string' &&
    'startDate' in value &&
    typeof value.startDate === 'string' &&
    'endDate' in value &&
    typeof value.endDate === 'string' &&
    'timeZone' in value &&
    typeof value.timeZone === 'string' &&
    'creatorId' in value &&
    typeof value.creatorId === 'string' &&
    'createdAt' in value &&
    typeof value.createdAt === 'string'
  )
}
