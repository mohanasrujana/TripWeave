import { useState, type FormEvent } from 'react'
import { createTrip, type Trip } from './tripApi'

type SubmissionState =
  | { kind: 'idle' }
  | { kind: 'submitting' }
  | { kind: 'success'; trip: Trip }
  | { kind: 'error'; message: string }

function TripForm() {
  const [submission, setSubmission] = useState<SubmissionState>({
    kind: 'idle',
  })

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setSubmission({ kind: 'submitting' })

    const formData = new FormData(event.currentTarget)

    try {
      const trip = await createTrip({
        name: String(formData.get('name')),
        startDate: String(formData.get('startDate')),
        endDate: String(formData.get('endDate')),
        timeZone: String(formData.get('timeZone')),
        creatorId: String(formData.get('creatorId')),
      })

      setSubmission({ kind: 'success', trip })
    } catch (error) {
      setSubmission({
        kind: 'error',
        message: error instanceof Error ? error.message : 'Unknown error',
      })
    }
  }

  return (
    <section aria-labelledby="create-trip-heading">
      <h2 id="create-trip-heading">Create a trip</h2>

      <form onSubmit={handleSubmit}>
        <label>
          Trip name
          <input name="name" maxLength={120} required />
        </label>

        <label>
          Start date
          <input name="startDate" type="date" required />
        </label>

        <label>
          End date
          <input name="endDate" type="date" required />
        </label>

        <label>
          Time zone
          <input
            name="timeZone"
            defaultValue={Intl.DateTimeFormat().resolvedOptions().timeZone}
            maxLength={64}
            required
          />
        </label>

        <label>
          Creator ID
          <input
            name="creatorId"
            placeholder="UUID"
            pattern="[0-9a-fA-F-]{36}"
            required
          />
        </label>

        <button type="submit" disabled={submission.kind === 'submitting'}>
          {submission.kind === 'submitting' ? 'Creating…' : 'Create trip'}
        </button>
      </form>

      {submission.kind === 'error' && (
        <p role="alert">Unable to create trip: {submission.message}</p>
      )}

      {submission.kind === 'success' && (
        <article aria-label="Saved trip">
          <h3>{submission.trip.name}</h3>
          <p>
            <time dateTime={submission.trip.startDate}>
              {submission.trip.startDate}
            </time>{' '}
            to{' '}
            <time dateTime={submission.trip.endDate}>
              {submission.trip.endDate}
            </time>
          </p>
          <p>Time zone: {submission.trip.timeZone}</p>
          <p>Trip ID: {submission.trip.id}</p>
        </article>
      )}
    </section>
  )
}

export default TripForm
