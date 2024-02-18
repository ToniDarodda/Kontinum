import { PartialPlusId } from "../utils";

interface BaseLeaderboardInterface {
    readonly id: string;
    readonly startTime?: Date;
    readonly endTime: Date
}

export type LeaderboardType = BaseLeaderboardInterface;

export type CreateLeaderboardType = Omit<BaseLeaderboardInterface, 'id'>

export type GetLeaderboardType = Pick<BaseLeaderboardInterface, 'id'>

export type PatchLeaderboardType = PartialPlusId<BaseLeaderboardInterface, 'id'>

export type DeleteLeaderboardType = Pick<BaseLeaderboardInterface, 'id'>