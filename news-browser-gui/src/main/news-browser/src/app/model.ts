import { InjectionToken } from '@angular/core';

export interface Config {
    domainUrl: string;
}

export const CONFIG = new InjectionToken<Config>('app.config')

