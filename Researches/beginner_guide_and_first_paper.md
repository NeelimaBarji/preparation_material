# Beginner's Guide + Your Actual First Paper

*Written for someone with strong engineering skills and no prior medical-research background.*

---

# PART 1 — The Beginner's Guide

## 1.1 What field are you actually entering?

You're entering **clinical informatics** — the field that studies how software behaves inside healthcare. It sits between computer science and medicine, and it has its own journals (JAMIA, npj Digital Medicine, JMIR), its own conferences, and — importantly — **its own rules about what counts as evidence.**

The single biggest culture shock coming from software engineering: in medicine, *"I built a thing and it works"* is not a result. **The result is the measurement.** A paper saying "we built a dosing verification pipeline" is weak. A paper saying "we measured how often LLMs violate pediatric dose ceilings, and here is the distribution" is strong. Medicine rewards careful measurement of reality far more than it rewards clever construction.

This is good news for you. Measurement is an engineering skill.

## 1.2 The core vocabulary, in plain language

**Systematic review (SR).** A literature review with the rigour of an experiment. Instead of "I read a bunch of papers and here's my take," you pre-declare exactly how you'll search, what you'll include, and how you'll judge quality — *then* you execute it. The point is that someone else could repeat your search and get the same set of papers. It's reproducible research applied to reading.

*Engineering analogy:* a narrative review is a blog post. A systematic review is a test suite with a documented spec, a pinned dependency list, and a CI log.

**Meta-analysis.** Statistically combining results from multiple studies into one pooled number. Not all systematic reviews contain one — and yours probably shouldn't (§1.4).

**Scoping review.** A lighter cousin of the SR. Used when a field is too young for firm conclusions and the useful contribution is *mapping what exists* rather than *judging what works*. Lower bar, entirely respectable, and often the right call for AI topics.

**PRISMA.** The reporting checklist for systematic reviews. Roughly 27 items you must report. Think of it as a linter for review papers — journals will reject you for failing it. PRISMA also defines the **flow diagram** (the funnel showing 2,000 records → 30 included studies) that every SR must publish.

**PROSPERO.** A public registry where you post your review plan *before you start*. This exists to stop researchers from quietly changing their question after seeing the data — the research equivalent of editing your tests to match the buggy output. Registration is expected; skipping it is a red flag.

**Protocol.** The plan document you register. That's what I wrote for you last turn.

**PICO / PICOTS.** A template for making a research question precise: **P**opulation, **I**ntervention, **C**omparator, **O**utcome (+ **T**iming, **S**etting). "Are LLMs good at pediatric dosing?" is not answerable. PICO forces it into: *In pediatric patients (P), do LLMs generating dosing content (I), compared with a guideline reference standard (C), produce dose-ceiling violations (O)?* — which is.

**Risk of bias (RoB).** For each study you include, you judge how much you can trust it. Was the grader blinded? Was the reference standard sound? Standardized tools exist for this. Note: "bias" here is a methodological term about study design flaws — **not** the fairness/ML sense of the word. This trips up almost everyone coming from an ML background.

**Reference standard / ground truth.** What you compare the model's answer against. In medicine, choosing this is a serious methodological decision, not an implementation detail — and reviewers will attack it. "We compared against the FDA label" is defensible. "We compared against what GPT-4 said was correct" is not.

**Grey literature / preprints.** arXiv and medRxiv papers that haven't been peer-reviewed. In CS these are first-class citizens. In medicine they're treated with suspicion. You must handle them explicitly.

**GRADE.** A framework for rating how confident we should be in a body of evidence overall. Built for clinical trials; fits AI benchmark studies awkwardly.

## 1.3 What your research area is actually about, in one paragraph

Hospitals are deploying LLMs to write clinical notes, draft messages to parents, and generate discharge instructions. Those documents contain **numbers** — drug doses, weights, concentrations, volumes. In adult medicine a small numeric error is often survivable. In pediatrics it frequently isn't: doses are calculated per kilogram of body weight, so a child who weighs 8 kg gets a dose 10× smaller than an adult, and a misplaced decimal point produces a tenfold overdose. Your research asks: **when LLMs handle pediatric numbers, how and where do they fail — and do the obvious fixes actually fix it?**

The non-obvious insight driving your whole program, and the thing that makes it publishable: **most people assume this is an arithmetic problem, so they bolt a calculator onto the model and declare victory.** But the dangerous failures aren't arithmetic. They're *setup* failures — using a weight that's four months stale, forgetting that weight-based dosing stops at an adult ceiling, confusing gestational age with chronological age, or producing a dose that's arithmetically perfect but requires measuring 0.32 mL with a kitchen spoon. A calculator cannot catch any of those, because in every one of them **the arithmetic is correct.**

That's your thesis. It's a good one.

## 1.4 Five things that will surprise you coming from software

1. **Negative results are publishable, and often better.** "This doesn't work as well as everyone assumes" is a real contribution here.
2. **You cannot pool everything.** Averaging accuracy percentages across studies that used different tasks and different ground truths produces a number that means nothing. This is currently the most common fatal flaw in AI systematic reviews. Resist it.
3. **Model versions are data.** "GPT-4" is not a specification. The same name refers to different models across months. Record exact version strings and access dates — reviewers now check.
4. **Peer review is slow.** Months, not days. Post a preprint to establish priority, then submit. Plan your timeline around latency you cannot control.
5. **Clinical claims need clinical authors.** You can measure that a model produced a dose above the label ceiling — that's an objective, checkable fact. You should *not* assert that a specific error would have harmed a specific child. Stay on the side of the line you can defend, or bring in a co-author who can cross it.

## 1.5 The credential question, addressed directly

You are a backend engineer without a clinical degree, and you will publish in clinical journals. This is genuinely done — clinical informatics is full of engineers — but two things are true:

- **Nobody will reject you for lacking an MD.** They will reject you for making clinical judgements you can't support.
- **A clinical co-author roughly doubles the ceiling of what you can claim,** and makes acceptance materially easier. A pediatric pharmacist is the ideal collaborator for dosing work — dose ceilings, formulations, and administration practicalities are *literally their specialty*, and they are far more approachable than a busy attending physician.

Practical route to finding one: post the preprint first, then email pediatric pharmacists who've published on medication safety, with the preprint attached and a specific ask. "I've built and released this benchmark, would you be interested in co-authoring the clinical validation?" gets answered. "Would you like to collaborate?" does not.

---

# PART 2 — Your Immediate First Paper

## 2.1 I'm revising my earlier advice

Last turn I put the systematic reviews at the base of your pyramid. That's right for *building a research identity* — but it's wrong as your *first action*, and you asking for something immediate makes that clear. Here's the honest reassessment:

The systematic review is **6–9 months, requires a collaborator for credible dual screening, and depends on library-science skills you don't have yet.** It's the wrong thing to attempt first. You'd be starting your research career by doing the one task that uses none of your actual strengths.

**Start with the dose-ceiling benchmark instead.** It's 6–8 weeks, fully solo, entirely synthetic, needs no IRB and no patient data — and it's a scripting-and-measurement problem, which is exactly what you've done for ten years. Do the SR second, once you have a published result and a collaborator that result attracted.

## 2.2 The paper

**Working title:** *"Correct Arithmetic, Unsafe Dose: Weight-Based Dose Ceiling Violations in LLM-Generated Pediatric Medication Guidance"*

**The one-sentence finding you're testing:** LLMs apply mg/kg dosing correctly but fail to cap at the adult maximum dose in heavier children and adolescents — and giving the model a calculator may make this *worse*, not better, by increasing its confidence in an uncapped product.

**Why this is a good first paper:**

- The result is **objectively checkable**. A dose either exceeds the label ceiling or it doesn't. No clinical judgement, no grading rubric, no inter-rater reliability. This sidesteps your credential gap entirely.
- The design is **trivially explainable** in one figure: error rate on the y-axis, patient weight on the x-axis. If the curve is flat until ~40 kg and then climbs, you have a paper. That single figure *is* the paper.
- The **tool-augmentation inversion** is a genuinely provocative claim. Most work assumes tool use is monotonically safer. If you show a case where it isn't, that's memorable — and memorable is what gets cited.
- Everything is **synthetic**. There is no patient, no consent, no IRB, no data-use agreement, nothing to wait for.

## 2.3 The design

**Independent variables**

| Variable | Levels |
|---|---|
| Simulated patient weight | 3 kg → 80 kg, stepped (e.g. 1 kg increments = 78 points) |
| Drug | 8–12 common pediatric drugs with published single-dose and daily ceilings |
| Model | 4–6 models, mixing open-weight and closed |
| Condition | (a) base model, (b) model + calculator tool, (c) model + explicit "check ceilings" prompt |
| Prompt phrasing | 3 paraphrases per case, to separate real failures from prompt luck |

**Dependent variables**

- Ceiling violation (binary): recommended dose > published single-dose max
- Daily-ceiling violation (binary)
- Magnitude of violation (% over ceiling)
- Whether the model *mentioned* a ceiling at all, even if it then ignored it — this distinguishes "doesn't know the rule" from "knows but doesn't apply it," and that distinction is the most interesting thing in your dataset

**The headline analysis:** violation rate as a function of weight, per model, per condition. Plus the crossover weight at which each drug's mg/kg product first exceeds its ceiling — that's the theoretical danger threshold you compare observed behaviour against.

**Run each cell 3–5 times.** LLMs are non-deterministic; a single run per condition is not a measurement, and reviewers will say so.

## 2.4 The ground-truth problem — read this carefully

Your entire paper rests on the ceiling values being right. **Do not let me, or any LLM, supply them.** I could produce confident, plausible, wrong dosing numbers — which is precisely the failure mode your research is about. Using AI-generated dose ceilings as ground truth for an AI dosing study would be a fatal and slightly embarrassing flaw.

Source them from, in order of preference:

1. **FDA structured product labels via the openFDA API** — programmatic, citable, free, versioned. This is your primary source and it fits your skillset.
2. **A pediatric dosing reference** (Lexicomp Pediatric, Harriet Lane Handbook) for anything the label doesn't cover cleanly.
3. **A pharmacist's review** of your final table before submission. Even an informal one. Ask for it explicitly.

Then **publish the table** as a supplementary file, with the source and retrieval date for every value. That transparency is itself a contribution — and it's what will make other people use your benchmark.

## 2.5 Week-by-week plan (6–8 weeks at ~8 hrs/week)

| Week | Work | Output |
|---|---|---|
| 1 | Pick drug panel. Pull ceilings from openFDA. Hand-verify every value against a second source. | `drug_ceilings.csv` — your ground truth |
| 2 | Build the prompt generator and the response parser (extracting a dose in mg from free text is the fiddly part — budget for it). | `generate_cases.py`, `parse_response.py` |
| 3 | Pilot on ONE model, 2 drugs, full weight sweep. Fix everything that breaks. | Pilot results, debugged pipeline |
| 4 | Full run: all models × drugs × weights × conditions × repeats. | `results.jsonl` |
| 5 | Analysis + figures. The weight-vs-violation-rate curve is the money plot. | Figures, results tables |
| 6 | Write. Methods first (easiest), then Results, then Discussion, Intro last. | Draft |
| 7 | Clean the repo, write the README, get a pharmacist to eyeball the ceiling table. | Public GitHub repo |
| 8 | Post preprint to arXiv/medRxiv. Then submit. | Preprint DOI |

**Write Methods first.** It's the section you can write before you have results, it's the least creative, and having it done removes the intimidation of a blank page.

## 2.6 Where to send it

| Venue | Fit | Note |
|---|---|---|
| **arXiv / medRxiv** | Do this first, always | Establishes priority immediately, costs nothing, lets you approach collaborators with something concrete |
| **JMIR Formative Research** | Strong fit | Explicitly scoped for early-stage/pilot evaluation work; fast; open access; publishes LLM evaluation studies |
| **JAMIA Open** | Strong fit | Informatics audience, open access, receptive to benchmark/dataset papers |
| **npj Digital Medicine** | Stretch | Only if the tool-augmentation inversion result is clean and striking |
| **Pediatric Research (correspondence)** | Alternative | They've published on LLM pediatric dosing already, so the topic is proven in-scope |

Aim for JMIR Formative Research or JAMIA Open for paper one. Save the ambitious venue for when you have a track record. A published paper in a mid-tier journal beats a perfect manuscript in your drafts folder.

## 2.7 The one thing most likely to sink this

**Response parsing.** Getting a clean numeric dose out of free-text LLM output is harder than it sounds — models say "give 480 mg (15 mL of the 160 mg/5 mL suspension) every 6 hours, not exceeding 5 doses daily," and your regex needs to pull the right number out of four candidates. Bad parsing silently corrupts every downstream result.

Mitigations: use structured output / function calling where the model supports it; hand-validate a random 100 parsed responses against the raw text and report that validation accuracy in your Methods (reviewers love this and almost nobody does it); log every raw response so you can re-parse without re-running.

## 2.8 What this unlocks

After this paper you have: a citable publication, a public benchmark others can build on, a concrete artifact to attract a clinical collaborator, and a demonstrated result to cite in the introduction of *every* subsequent paper. **Then** do the systematic review — with a co-author, and with the credibility of someone who's already published in the space.

---

## Sources

- [PRISMA 2020 statement](https://www.prisma-statement.org/prisma-2020)
- [How to register a systematic review in PROSPERO — CRD York](https://www.crd.york.ac.uk/PROSPERO/help/register)
- [openFDA Drug Labeling API](https://open.fda.gov/apis/drug/label/)
- [openFDA example API queries](https://open.fda.gov/apis/drug/label/example-api-queries)
- [openFDA fields reference](https://open.fda.gov/apis/openfda-fields/)
- [JMIR Formative Research — Focus and Scope](https://formative.jmir.org/about-journal/focus-and-scope)
- [The TRIPOD-LLM reporting guideline for studies using large language models — Nature Medicine](https://www.nature.com/articles/s41591-024-03425-5)
- [Reporting guideline for chatbot health advice studies: the CHART statement — PMC](https://pmc.ncbi.nlm.nih.gov/articles/PMC12320030/)
- [Can large language models assist with pediatric dosing accuracy? — Pediatric Research](https://www.nature.com/articles/s41390-025-03980-8)
- [Large language models and clinical calculations: to err is human and machines are not exempt — Pediatric Research](https://www.nature.com/articles/s41390-025-04166-y)
- [Large language model agents can use tools to perform clinical calculations — npj Digital Medicine](https://www.nature.com/articles/s41746-025-01475-8)
- [Tenfold medication errors: 5 years' experience at a university-affiliated pediatric hospital — PubMed](https://pubmed.ncbi.nlm.nih.gov/22473367/)
