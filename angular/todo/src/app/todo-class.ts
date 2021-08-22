export class TodoClass {
    id: number;
    title: string;
    state: boolean;
    timeCreated: Date;
    lastUpdated: Date;

    constructor(id: number, title: string, state: boolean, timeCreated: Date, lastUpdated: Date)    {
        this.id = id;
        this.title = title;
        this.state = state;
        this.timeCreated = timeCreated;
        this.lastUpdated = lastUpdated;
    }
}
